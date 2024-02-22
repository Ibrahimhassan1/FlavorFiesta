package com.softups.flavorfiesta.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.twotone.Fastfood
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.softups.flavorfiesta.R
import com.softups.flavorfiesta.ui.recipe_detail.RecipeDetailScreen
import com.softups.flavorfiesta.ui.recipe_list.RecipeListScreen
import com.softups.flavorfiesta.ui.recipe_list.RecipeListViewModel
import com.softups.flavorfiesta.ui.theme.FlavorFiestaTheme

@Composable
fun FlavorFiestaApp(
    widthSizeClass: WindowWidthSizeClass,
    recipeListViewModel: RecipeListViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the route of the current screen then turn it to a title
    val screenTitle =
        when (backStackEntry?.destination?.route ?: Screen.RecipeListScreen.route) {
            Screen.RecipeListScreen.route -> stringResource(id = R.string.recipe_list_title)
            Screen.RecipeDetailScreen.route -> stringResource(id = R.string.recipe_detail_title)
            else -> stringResource(id = R.string.recipe_list_title)
        }

    FlavorFiestaTheme {
        Scaffold(
            topBar = {
                AppBar(
                    screenTitle = screenTitle,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() },
                    modifier = Modifier
                )
            }
        ) { innerPadding ->
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                color = MaterialTheme.colorScheme.background
            ) {
                AppNavHost(navController, recipeListViewModel, widthSizeClass)
            }
        }
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    recipeListViewModel: RecipeListViewModel = hiltViewModel(),
    widthSizeClass: WindowWidthSizeClass
) {
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
                    recipeListViewModel.setSelectedRecipe(it)
                    navController.navigate(Screen.RecipeDetailScreen.route)
                },
                onRefreshClick = {
                    recipeListViewModel.getRecipes()
                },
                widthSizeClass = widthSizeClass
            )
        }
        composable(
            route = Screen.RecipeDetailScreen.route
        ) {
            recipeListViewModel.state.value.selectedRecipe?.let {
                RecipeDetailScreen(
                    recipe = it
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    screenTitle: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.TwoTone.Fastfood,
                    // Content Description is not needed here - image is decorative, and setting a
                    // null content description allows accessibility services to skip this element
                    // during navigation.
                    contentDescription = null
                )
                Text(
                    text = screenTitle,
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}


