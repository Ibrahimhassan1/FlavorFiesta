package com.softups.flavorfiesta.ui.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.softups.flavorfiesta.ui.recipe_detail.RecipeDetailScreen
import com.softups.flavorfiesta.ui.recipe_detail.RecipeDetailsDestination
import com.softups.flavorfiesta.ui.recipe_list.RecipeListDestination
import com.softups.flavorfiesta.ui.recipe_list.RecipeListScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    widthSizeClass: WindowWidthSizeClass
) {
    NavHost(
        navController = navController,
        startDestination = RecipeListDestination.route
    ) {
        composable(
            route = RecipeListDestination.route
        ) {
            RecipeListScreen(
                navController = navController,
                widthSizeClass = widthSizeClass
            )
        }
        composable(
            route = RecipeDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(RecipeDetailsDestination.recipeIdArg) { NavType.IntType })
        ) {
            RecipeDetailScreen(
                widthSizeClass = widthSizeClass
            )
        }
    }
}