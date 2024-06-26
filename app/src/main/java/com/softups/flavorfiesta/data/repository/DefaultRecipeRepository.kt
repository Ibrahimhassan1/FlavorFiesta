package com.softups.flavorfiesta.data.repository

import com.softups.flavorfiesta.data.DataSource
import com.softups.flavorfiesta.data.remote.RemoteDataSource
import com.softups.flavorfiesta.data.remote.dto.RecipeDto
import com.softups.flavorfiesta.domain.repository.RecipeRepository
import javax.inject.Inject

class DefaultRecipeRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: DataSource
) :
    RecipeRepository {
    override suspend fun getRecipes(): List<RecipeDto> {
            val remoteRecipes = remoteDataSource.fetchRecipes()
            val localRecipes = localDataSource.fetchRecipes()
            // assume remote data is the source of truth
            if (remoteRecipes != localRecipes) {
                localDataSource.updateRecipes(remoteRecipes)
            }
        return localDataSource.fetchRecipes()
    }

    override suspend fun getRecipe(recipeId: Int): RecipeDto {
        return localDataSource.getRecipe(recipeId)
    }
}