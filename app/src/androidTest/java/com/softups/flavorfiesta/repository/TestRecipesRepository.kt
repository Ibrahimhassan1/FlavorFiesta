package com.softups.flavorfiesta.repository

import com.softups.flavorfiesta.common.TestUtils
import com.softups.flavorfiesta.data.remote.dto.RecipeDto
import com.softups.flavorfiesta.domain.repository.RecipeRepository

class TestRecipesRepository : RecipeRepository {
    override suspend fun getRecipes(): List<RecipeDto> {
        return TestUtils.dummyRecipesDto.recipes
    }

    override suspend fun getRecipe(recipeId: Int): RecipeDto {
        return TestUtils.dummyRecipesDto.recipes[0]
    }

}