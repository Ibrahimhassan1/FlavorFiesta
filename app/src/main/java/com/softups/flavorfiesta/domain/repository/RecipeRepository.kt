package com.softups.flavorfiesta.domain.repository

import com.softups.flavorfiesta.data.remote.dto.RecipeDto

interface RecipeRepository {
    suspend fun getRecipes(): List<RecipeDto>
    suspend fun getRecipe(recipeId: Int): RecipeDto
}