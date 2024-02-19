package com.softups.flavorfiesta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.softups.flavorfiesta.ui.Screen
import com.softups.flavorfiesta.ui.recipe_list.RecipeListScreen
import com.softups.flavorfiesta.ui.recipe_list.RecipeListViewModel
import com.softups.flavorfiesta.ui.theme.FlavorFiestaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val recipeListViewModel: RecipeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlavorFiestaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.RecipeListScreen.route
                    ) {
                        composable(
                            route = Screen.RecipeListScreen.route
                        ) {
                            RecipeListScreen(
                                recipeListState = recipeListViewModel.state.value,
                                onItemClick = {
                                    navController.navigate(Screen.RecipeDetailScreen.route)
                                }
                            )
                        }
                        composable(
                            route = Screen.RecipeDetailScreen.route
                        ) {
                        }
                    }
                }
            }
        }
    }
}
