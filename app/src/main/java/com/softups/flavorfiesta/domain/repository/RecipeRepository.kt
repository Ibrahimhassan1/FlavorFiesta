package com.softups.flavorfiesta.domain.repository

import com.softups.flavorfiesta.data.remote.dto.RecipesDto

interface RecipeRepository {
    suspend fun getRecipes(): RecipesDto
}