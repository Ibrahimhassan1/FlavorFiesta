package com.softups.flavorfiesta.ui.recipe_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softups.flavorfiesta.common.Constants.UN_EXPECTED_ERROR
import com.softups.flavorfiesta.common.Resource
import com.softups.flavorfiesta.domain.use_case.GetRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getRecipeUseCase: GetRecipeUseCase
) : ViewModel() {

    private val recipeId: String =
        checkNotNull(savedStateHandle[RecipeDetailsDestination.recipeIdArg])

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val state: StateFlow<RecipeDetailsState> = getRecipeUseCase(recipeId.toInt()).map { result ->
        when (result) {
            is Resource.Success -> {
                RecipeDetailsState(
                    selectedRecipe = result.data!!
                )
            }

            is Resource.Error -> {
                RecipeDetailsState(
                    isLoading = false,
                    error = result.message ?: UN_EXPECTED_ERROR
                )
            }

            is Resource.Loading -> {
                RecipeDetailsState(
                    isLoading = true
                )
            }
        }
    }.stateIn(
        scope = viewModelScope,
        initialValue = RecipeDetailsState(),
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS)
    )
}