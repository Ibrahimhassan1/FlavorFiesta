package com.softups.flavorfiesta.data

import com.softups.flavorfiesta.data.remote.dto.RecipeDto

class TestLocalDataSource : DataSource {

    private val localRecipes = mutableListOf<RecipeDto>()

    override suspend fun fetchRecipes(): List<RecipeDto> {
        return localRecipes
    }

    override suspend fun updateRecipes(recipes: List<RecipeDto>) {
        localRecipes.addAll(recipes)
    }

}