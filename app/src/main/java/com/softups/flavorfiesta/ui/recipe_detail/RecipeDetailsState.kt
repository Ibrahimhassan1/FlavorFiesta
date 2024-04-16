package com.softups.flavorfiesta.ui.recipe_detail

import com.softups.flavorfiesta.domain.model.Recipe

data class RecipeDetailsState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
    val error: String = "",
    val selectedRecipe: Recipe? = null
)
