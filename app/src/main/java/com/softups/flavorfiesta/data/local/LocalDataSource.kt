package com.softups.flavorfiesta.data.local

import com.softups.flavorfiesta.data.DataSource
import com.softups.flavorfiesta.data.remote.dto.RecipeDto
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val recipesDao: RecipesDao) : DataSource {
    override suspend fun fetchRecipes(): List<RecipeDto> {
        return recipesDao.getRecipes()
    }

    override suspend fun getRecipe(recipeId: Int): RecipeDto {
        return recipesDao.getRecipeById(recipeId)
    }

    override suspend fun updateRecipes(recipes: List<RecipeDto>) {
        recipesDao.insertAllRecipes(recipes)
    }
}