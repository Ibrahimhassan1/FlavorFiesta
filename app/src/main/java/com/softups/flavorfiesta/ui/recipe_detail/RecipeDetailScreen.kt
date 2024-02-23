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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.softups.flavorfiesta.common.TestUtils
import com.softups.flavorfiesta.domain.model.Recipe
import com.softups.flavorfiesta.ui.recipe_detail.components.RecipeDetailScreenLandscape
import com.softups.flavorfiesta.ui.recipe_detail.components.RecipeDetailScreenPortrait
import com.softups.flavorfiesta.ui.theme.FlavorFiestaTheme

@Composable
fun RecipeDetailScreen(
    modifier: Modifier = Modifier,
    recipe: Recipe,
    widthSizeClass: WindowWidthSizeClass = WindowWidthSizeClass.Compact
) {

    val isExpanded = remember { widthSizeClass != WindowWidthSizeClass.Compact }
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {

        if (isExpanded) {
            RecipeDetailScreenLandscape(recipe = recipe)
        } else {
            RecipeDetailScreenPortrait(recipe = recipe)
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
            RecipeDetailScreen(recipe = TestUtils.singleDummyRecipe)
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
            RecipeDetailScreen(recipe = TestUtils.singleDummyRecipe)
        }
    }
}
