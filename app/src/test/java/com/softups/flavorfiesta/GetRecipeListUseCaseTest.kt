package com.softups.flavorfiesta

import MainDispatcherRule
import com.softups.flavorfiesta.common.Resource
import com.softups.flavorfiesta.common.TestUtils
import com.softups.flavorfiesta.data.remote.dto.toRecipes
import com.softups.flavorfiesta.domain.use_case.GetRecipesUseCase
import com.softups.flavorfiesta.repository.TestRecipesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test


class GetRecipeListUseCaseTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val recipesRepository = TestRecipesRepository()

    val getRecipesUseCase = GetRecipesUseCase(recipesRepository)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun whenInvoked_emitLoadingState_thenSuccessWithData() = runTest {
        val flowableRecipesUseCase = getRecipesUseCase()

        flowableRecipesUseCase.collect { result ->
            when (result) {
                is Resource.Loading -> {
                    assertEquals(result.data, null)
                    assertEquals(result.message, null)
                    advanceUntilIdle()
                }

                is Resource.Error -> {
                    assertNotEquals(result.message, null)
                    advanceUntilIdle()
                }

                is Resource.Success -> {
                    assertArrayEquals(
                        result.data?.toTypedArray(),
                        TestUtils.dummyRecipesDto.toRecipes().toTypedArray()
                    )
                }
            }
        }
    }
}