package com.softups.flavorfiesta

import MainDispatcherRule
import com.softups.flavorfiesta.common.Constants.HTTP_400_ERROR
import com.softups.flavorfiesta.common.Constants.NOT_CONNECTED_ERROR
import com.softups.flavorfiesta.common.Resource
import com.softups.flavorfiesta.common.TestUtils
import com.softups.flavorfiesta.data.TestLocalDataSource
import com.softups.flavorfiesta.data.remote.RecipeApi
import com.softups.flavorfiesta.data.remote.RemoteDataSource
import com.softups.flavorfiesta.data.remote.dto.toRecipes
import com.softups.flavorfiesta.data.repository.DefaultRecipeRepository
import com.softups.flavorfiesta.domain.use_case.GetRecipesUseCase
import com.softups.flavorfiesta.ui.recipe_list.RecipeListViewModel
import com.softups.flavorfiesta.util.enqueueResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

class RecipeListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    private var testDispatcher = UnconfinedTestDispatcher()

    private val mockWebServer = MockWebServer()
    private lateinit var getRecipesUseCase: GetRecipesUseCase
    private lateinit var localDataSource: TestLocalDataSource
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var recipesRepository: DefaultRecipeRepository
    private lateinit var recipeListViewModel: RecipeListViewModel

    private val client = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .build()

    private val recipeApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(mockWebServer.url("/"))
        .build().create(RecipeApi::class.java)

    @After
    fun reset() {
        mockWebServer.shutdown()
    }

    @Before
    fun setUp() {
        remoteDataSource = RemoteDataSource(recipeApi)
        localDataSource = TestLocalDataSource()
        recipesRepository =
            DefaultRecipeRepository(remoteDataSource, localDataSource, dispatcher = testDispatcher)
        getRecipesUseCase = GetRecipesUseCase(recipesRepository)
        recipeListViewModel = RecipeListViewModel(getRecipesUseCase)
    }

    @Test
    fun `when viewModel init then viewModel emits http error testing loading viewModel state`() =
        runTest {
            mockWebServer.enqueueResponse("recipes.json", HttpURLConnection.HTTP_OK)
            assertEquals(recipeListViewModel.state.value.isLoading, true)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when normal request is received then emit recipes list`() = runTest {
        mockWebServer.enqueueResponse("recipes.json", HttpURLConnection.HTTP_OK)
        val flowableRecipesUseCase = getRecipesUseCase()
        flowableRecipesUseCase.collect { result ->
            val recipeListViewModelState = recipeListViewModel.state.value
            when (result) {
                is Resource.Loading -> {
                    assertEquals(result.data, null)
                    assertEquals(result.message, null)
                    advanceUntilIdle()
                }

                is Resource.Error -> {}

                is Resource.Success -> {
                    assertEquals(result.message, null)
                    assertEquals(recipeListViewModelState.isLoading, false)
                    Assert.assertArrayEquals(
                        recipeListViewModelState.recipes.toTypedArray(),
                        TestUtils.dummyRecipesDto.toRecipes().toTypedArray()
                    )
                    Assert.assertArrayEquals(
                        localDataSource.fetchRecipes().toTypedArray(),
                        TestUtils.dummyRecipesDto.toRecipes().toTypedArray()
                    )
                }
            }
        }
    }

    @Test
    fun `when bad request then viewModel emits http error`() = runTest {
        mockWebServer.enqueueResponse("recipes.json", HttpURLConnection.HTTP_BAD_REQUEST)
        val flowableRecipesUseCase = getRecipesUseCase()
        flowableRecipesUseCase.collect { result ->
            val recipeListViewModelState = recipeListViewModel.state.value
            when (result) {
                is Resource.Loading -> {
                    assertEquals(result.data, null)
                    assertEquals(result.message, null)
                }

                is Resource.Error -> {
                    assertEquals(result.message, NOT_CONNECTED_ERROR)
                    assertEquals(recipeListViewModelState.isLoading, false)
                    Assert.assertTrue(recipeListViewModelState.recipes.isEmpty())
                    assertEquals(recipeListViewModelState.error, HTTP_400_ERROR)
                }

                is Resource.Success -> {}
            }
        }
    }

    @Test
    fun `when item selected then viewModel state emits state with selected recipe`() = runTest {
        recipeListViewModel.setSelectedRecipe(TestUtils.singleDummyRecipe)
        assertEquals(recipeListViewModel.state.value.selectedRecipe, TestUtils.singleDummyRecipe)
    }
}
