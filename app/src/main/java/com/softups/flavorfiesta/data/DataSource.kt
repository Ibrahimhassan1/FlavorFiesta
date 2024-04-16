package com.softups.flavorfiesta.data

import com.softups.flavorfiesta.data.remote.dto.RecipeDto

interface DataSource {
    suspend fun fetchRecipes(): List<RecipeDto>
    suspend fun getRecipe(recipeId: Int): RecipeDto
    suspend fun updateRecipes(recipes: List<RecipeDto>)

}