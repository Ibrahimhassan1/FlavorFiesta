package com.softups.flavorfiesta.data.remote

import com.softups.flavorfiesta.data.DataSource
import com.softups.flavorfiesta.data.remote.dto.RecipeDto
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val recipeApi: RecipeApi) : DataSource {
    override suspend fun fetchRecipes(): List<RecipeDto> {
        return recipeApi.getRemoteRecipes().recipes
    }

    override suspend fun getRecipe(recipeId: Int): RecipeDto {
        TODO("Not yet implemented")
    }

    override suspend fun updateRecipes(recipes: List<RecipeDto>) {
        //Not yet implemented
    }
}