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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.softups.flavorfiesta.R
import com.softups.flavorfiesta.ui.navigation.AppNavHost
import com.softups.flavorfiesta.ui.recipe_detail.RecipeDetailsDestination
import com.softups.flavorfiesta.ui.recipe_list.RecipeListDestination
import com.softups.flavorfiesta.ui.theme.FlavorFiestaTheme

@Composable
fun FlavorFiestaApp(
    widthSizeClass: WindowWidthSizeClass
) {
    val navController = rememberNavController()
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the route of the current screen then turn it to a title
    val screenTitle =
        when (backStackEntry?.destination?.route ?: RecipeListDestination.route) {
            RecipeListDestination.route -> stringResource(id = R.string.recipe_list_title)
            RecipeDetailsDestination.route -> stringResource(id = R.string.recipe_detail_title)
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
                AppNavHost(navController, widthSizeClass)
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
