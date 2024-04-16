package com.softups.flavorfiesta.repository

import com.softups.flavorfiesta.data.DataSource
import com.softups.flavorfiesta.data.remote.dto.RecipeDto
import com.softups.flavorfiesta.domain.repository.RecipeRepository

class TestRecipesRepository(private val localDataSource: DataSource) : RecipeRepository {

    override suspend fun getRecipes(): List<RecipeDto> {
        return localDataSource.fetchRecipes()
    }

    override suspend fun getRecipe(recipeId: Int): RecipeDto {
        return localDataSource.getRecipe(recipeId)
    }

}