package com.softups.flavorfiesta.domain.use_case

import com.softups.flavorfiesta.common.Constants.NOT_CONNECTED_ERROR
import com.softups.flavorfiesta.common.Constants.UN_EXPECTED_ERROR
import com.softups.flavorfiesta.common.Resource
import com.softups.flavorfiesta.data.remote.dto.toRecipes
import com.softups.flavorfiesta.domain.model.Recipe
import com.softups.flavorfiesta.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {

    operator fun invoke(): Flow<Resource<List<Recipe>>> = flow {
        try {
            emit(Resource.Loading())
            val recipeList = recipeRepository.getRecipes().toRecipes()
            emit(Resource.Success(recipeList))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: UN_EXPECTED_ERROR))
        } catch (e: IOException) {
            emit(Resource.Error(NOT_CONNECTED_ERROR))
        }
    }
}
