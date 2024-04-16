package com.softups.flavorfiesta.ui.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.softups.flavorfiesta.ui.recipe_detail.RecipeDetailScreen
import com.softups.flavorfiesta.ui.recipe_detail.RecipeDetailsDestination
import com.softups.flavorfiesta.ui.recipe_list.RecipeListDestination
import com.softups.flavorfiesta.ui.recipe_list.RecipeListScreen
import com.softups.flavorfiesta.ui.recipe_list.RecipeListViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    recipeListViewModel: RecipeListViewModel = hiltViewModel(),
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
                recipeListState = recipeListViewModel.state.value,
                onItemClick = {
                    navController.navigate("${RecipeDetailsDestination.route}/${it.id}")
                },
                onRefreshClick = {
                    recipeListViewModel.getRecipes()
                },
                widthSizeClass = widthSizeClass
            )
        }
        composable(
            route = RecipeDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(RecipeDetailsDestination.recipeIdArg) { NavType.IntType })
        ) { backStackEntry ->

            backStackEntry.arguments?.getString(RecipeDetailsDestination.recipeIdArg)
                ?.let { recipeId ->
                    RecipeDetailScreen(
                        recipeId = recipeId,
                        widthSizeClass = widthSizeClass
                    )
                }

        }
    }
}