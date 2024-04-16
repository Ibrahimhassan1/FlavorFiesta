package com.softups.flavorfiesta.data.remote

import com.softups.flavorfiesta.data.remote.dto.RecipeDto
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val recipeApi: RecipeApi) {
    suspend fun fetchRecipes(): List<RecipeDto> {
        return recipeApi.getRemoteRecipes().recipes
    }
}