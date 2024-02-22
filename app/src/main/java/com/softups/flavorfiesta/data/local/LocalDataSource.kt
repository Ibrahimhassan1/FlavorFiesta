package com.softups.flavorfiesta.data.local

import com.softups.flavorfiesta.data.DataSource
import com.softups.flavorfiesta.data.remote.dto.RecipeDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val recipesDao: RecipesDao) : DataSource {
    override suspend fun fetchRecipes(): List<RecipeDto> {
        return withContext(Dispatchers.IO) {
            recipesDao.getRecipes()
        }
    }

    override suspend fun updateRecipes(recipes: List<RecipeDto>) {
        withContext(Dispatchers.IO) {
            recipesDao.insertAllRecipes(recipes)
        }
    }
}