package com.softups.flavorfiesta.ui.recipe_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.softups.flavorfiesta.R
import com.softups.flavorfiesta.ui.navigation.NavigationDestination
import com.softups.flavorfiesta.ui.recipe_detail.components.RecipeDetailScreenLandscape
import com.softups.flavorfiesta.ui.recipe_detail.components.RecipeDetailScreenPortrait

object RecipeDetailsDestination : NavigationDestination {
    override val route = "recipe_detail_screen"
    override val titleResource = R.string.recipe_detail_title
    const val recipeIdArg = "recipeId"
    val routeWithArgs = "$route/{$recipeIdArg}"
}

@Composable
fun RecipeDetailScreen(
    modifier: Modifier = Modifier,
    widthSizeClass: WindowWidthSizeClass = WindowWidthSizeClass.Compact,
    viewModel: RecipeDetailsViewModel = hiltViewModel()
) {
    val isExpanded = remember { widthSizeClass != WindowWidthSizeClass.Compact }
    val recipeDetailsState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        if (isExpanded) {
            RecipeDetailScreenLandscape(recipeDetailsState = recipeDetailsState)
        } else {
            RecipeDetailScreenPortrait(recipeDetailsState = recipeDetailsState)
        }
    }
}

