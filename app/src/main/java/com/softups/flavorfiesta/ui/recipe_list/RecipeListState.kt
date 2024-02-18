package com.softups.flavorfiesta.ui.recipe_list

import com.softups.flavorfiesta.domain.model.Recipe

data class RecipeListState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
    val error: String = ""
)
