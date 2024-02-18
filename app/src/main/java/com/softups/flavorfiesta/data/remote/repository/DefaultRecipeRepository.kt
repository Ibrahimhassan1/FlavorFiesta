package com.softups.flavorfiesta.data.remote.repository

import com.softups.flavorfiesta.data.remote.RecipeApi
import com.softups.flavorfiesta.data.remote.dto.RecipesDto
import com.softups.flavorfiesta.domain.repository.RecipeRepository
import javax.inject.Inject

class DefaultRecipeRepository @Inject constructor(private val recipeApi: RecipeApi) :
    RecipeRepository {
    override suspend fun getRecipes(): RecipesDto {
        return recipeApi.getRecipes()
    }
}