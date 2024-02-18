package com.softups.flavorfiesta.data.remote

import com.softups.flavorfiesta.data.remote.dto.RecipesDto
import retrofit2.http.GET

interface RecipeApi {

    @GET("recipes/")
    suspend fun getRecipes(): RecipesDto
}