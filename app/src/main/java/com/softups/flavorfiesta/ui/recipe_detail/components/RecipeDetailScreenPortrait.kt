package com.softups.flavorfiesta.ui.recipe_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import com.softups.flavorfiesta.R
import com.softups.flavorfiesta.common.TestUtils
import com.softups.flavorfiesta.data.remote.dto.toRecipes
import com.softups.flavorfiesta.ui.recipe_detail.RecipeDetailsState
import com.softups.flavorfiesta.ui.theme.FlavorFiestaTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RecipeDetailScreenPortrait(
    modifier: Modifier = Modifier,
    recipeDetailsState: RecipeDetailsState,
) {
    recipeDetailsState.selectedRecipe?.let { recipe ->
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .semantics { testTagsAsResourceId = true },
            verticalArrangement = Arrangement.Center
        ) {
            DetailHeader(recipe = recipe)
            Spacer(modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small)))
            DetailRecipeInfo(recipe = recipe)
            Spacer(modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small)))
            DetailRecipeIngredients(recipe = recipe)
            Spacer(modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small)))
            DetailRecipeInstructions(recipe = recipe)

        }
    }
}

@Preview(showBackground = true, widthDp = 440, heightDp = 840)
@Composable
fun RecipeDetailPortraitPreview() {
    FlavorFiestaTheme(dynamicColor = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RecipeDetailScreenPortrait(recipeDetailsState = RecipeDetailsState(selectedRecipe = TestUtils.dummyRecipesDto.toRecipes()[0]))
        }
    }
}