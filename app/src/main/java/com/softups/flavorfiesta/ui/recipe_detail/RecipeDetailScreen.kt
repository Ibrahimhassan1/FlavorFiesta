package com.softups.flavorfiesta.ui.recipe_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.softups.flavorfiesta.R
import com.softups.flavorfiesta.common.TestUtils
import com.softups.flavorfiesta.ui.navigation.NavigationDestination
import com.softups.flavorfiesta.ui.recipe_detail.components.RecipeDetailScreenLandscape
import com.softups.flavorfiesta.ui.recipe_detail.components.RecipeDetailScreenPortrait
import com.softups.flavorfiesta.ui.theme.FlavorFiestaTheme

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
    recipeId: String,
    viewModel: RecipeDetailsViewModel = hiltViewModel()
) {

    val isExpanded = remember { widthSizeClass != WindowWidthSizeClass.Compact }
    val recipeDetailsState by viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.getRecipe(recipeId.toInt())
    }
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {

        if (isExpanded) {
            RecipeDetailScreenLandscape(recipeDetailsState = recipeDetailsState)
        } else {
            RecipeDetailScreenPortrait(recipeDetailsState = recipeDetailsState)
        }
    }
}

@Preview
@Composable
fun RecipeDetailScreenPreview() {
    FlavorFiestaTheme(dynamicColor = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RecipeDetailScreen(recipeId = TestUtils.singleDummyRecipe.id.toString())
        }
    }
}

@Preview(showBackground = true, widthDp = 700, heightDp = 500)
@Composable
fun RecipeDetailScreenPreviewLandscape() {
    FlavorFiestaTheme(dynamicColor = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RecipeDetailScreen(recipeId = TestUtils.singleDummyRecipe.id.toString())
        }
    }
}
