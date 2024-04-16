package com.softups.flavorfiesta.ui.recipe_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softups.flavorfiesta.common.Constants.UN_EXPECTED_ERROR
import com.softups.flavorfiesta.common.Resource
import com.softups.flavorfiesta.domain.model.Recipe
import com.softups.flavorfiesta.domain.use_case.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

    var state = mutableStateOf(RecipeListState())
        private set

    init {
        getRecipes()
    }

    fun getRecipes() {
        getRecipesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    state.value = RecipeListState(
                        recipes = result.data ?: emptyList()
                    )
                }

                is Resource.Error -> {
                    state.value = RecipeListState(
                        isLoading = false,
                        error = result.message ?: UN_EXPECTED_ERROR
                    )
                }

                is Resource.Loading -> {
                    state.value = RecipeListState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setSelectedRecipe(recipe: Recipe) {
        state.value = state.value.copy(
            selectedRecipe = recipe
        )
    }
}