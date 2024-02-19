package com.softups.flavorfiesta.ui

sealed class Screen(val route: String) {
    data object RecipeListScreen : Screen("recipe_list_screen")
    data object RecipeDetailScreen : Screen("recipe_detail_screen")
}