package com.softups.flavorfiesta.repository

import com.softups.flavorfiesta.common.TestUtils
import com.softups.flavorfiesta.data.remote.dto.RecipesDto
import com.softups.flavorfiesta.domain.repository.RecipeRepository

class TestRecipesRepository : RecipeRepository {
    override suspend fun getRecipes(): RecipesDto {
        return TestUtils.dummyRecipesDto
    }

}