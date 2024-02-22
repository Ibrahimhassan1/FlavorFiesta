package com.softups.flavorfiesta.use_case

import MainDispatcherRule
import com.softups.flavorfiesta.common.Resource
import com.softups.flavorfiesta.common.TestUtils
import com.softups.flavorfiesta.data.DataSource
import com.softups.flavorfiesta.data.TestLocalDataSource
import com.softups.flavorfiesta.data.remote.dto.toRecipes
import com.softups.flavorfiesta.domain.use_case.GetRecipesUseCase
import com.softups.flavorfiesta.repository.TestRecipesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetRecipeListUseCaseTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var testLocalDataSource: DataSource

    private lateinit var recipesRepository: TestRecipesRepository

    private lateinit var getRecipesUseCase: GetRecipesUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    private var testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        testLocalDataSource = TestLocalDataSource()
        recipesRepository = TestRecipesRepository(testLocalDataSource)
        getRecipesUseCase = GetRecipesUseCase(recipesRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when GetRecipeListUseCase invoked then viewModel emits LoadingState then SuccessWithData state`() =
        runTest {
            assert(testLocalDataSource.fetchRecipes().isEmpty())

            // insert test recipes into local data source
            testLocalDataSource.updateRecipes(TestUtils.dummyRecipesDto.recipes)

            getRecipesUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        assertEquals(result.data, null)
                        assertEquals(result.message, null)
                        advanceUntilIdle()
                    }

                    is Resource.Error -> {
                        assertNotEquals(result.message, null)
                    }

                    is Resource.Success -> {
                        assertArrayEquals(
                            testLocalDataSource.fetchRecipes().toTypedArray(),
                            TestUtils.dummyRecipesDto.recipes.toTypedArray()
                        )

                        assertArrayEquals(
                            result.data?.toTypedArray(),
                            TestUtils.dummyRecipesDto.toRecipes().toTypedArray()
                        )
                    }
                }
            }
        }
}