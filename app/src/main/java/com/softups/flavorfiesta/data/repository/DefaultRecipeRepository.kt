package com.softups.flavorfiesta.data.repository

import com.softups.flavorfiesta.data.DataSource
import com.softups.flavorfiesta.data.remote.dto.RecipeDto
import com.softups.flavorfiesta.domain.repository.RecipeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultRecipeRepository @Inject constructor(
    private val remoteDataSource: DataSource,
    private val localDataSource: DataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    RecipeRepository {
    override suspend fun getRecipes(): List<RecipeDto> {
        return withContext(dispatcher) {
            val remoteRecipes = remoteDataSource.fetchRecipes()
            val localRecipes = localDataSource.fetchRecipes()
            // assume remote data is the source of truth
            if (remoteRecipes != localRecipes) {
                localDataSource.updateRecipes(remoteRecipes)
            }
            localDataSource.fetchRecipes()
        }
    }

}