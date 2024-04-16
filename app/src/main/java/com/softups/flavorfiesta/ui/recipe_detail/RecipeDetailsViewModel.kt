package com.softups.flavorfiesta.ui.recipe_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softups.flavorfiesta.common.Constants.UN_EXPECTED_ERROR
import com.softups.flavorfiesta.common.Resource
import com.softups.flavorfiesta.domain.use_case.GetRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRecipeUseCase: GetRecipeUseCase
) : ViewModel() {

    private val recipeId: String =
        checkNotNull(savedStateHandle[RecipeDetailsDestination.recipeIdArg])
    private val _state = mutableStateOf(RecipeDetailsState())
    val state: State<RecipeDetailsState> = _state

    init {
        getRecipe(recipeId.toInt())
    }

    fun getRecipe(recipeId: Int) {
        getRecipeUseCase(recipeId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = RecipeDetailsState(
                        selectedRecipe = result.data!!
                    )
                }

                is Resource.Error -> {
                    _state.value = RecipeDetailsState(
                        isLoading = false,
                        error = result.message ?: UN_EXPECTED_ERROR
                    )
                }

                is Resource.Loading -> {
                    _state.value = RecipeDetailsState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}