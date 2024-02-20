package com.softups.flavorfiesta.ui.recipe_detail

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
import com.softups.flavorfiesta.domain.model.Recipe
import com.softups.flavorfiesta.ui.recipe_detail.components.DetailHeader
import com.softups.flavorfiesta.ui.recipe_detail.components.DetailRecipeInfo
import com.softups.flavorfiesta.ui.recipe_detail.components.DetailRecipeIngredients
import com.softups.flavorfiesta.ui.recipe_detail.components.DetailRecipeInstructions
import com.softups.flavorfiesta.ui.theme.FlavorFiestaTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RecipeDetailScreen(
    modifier: Modifier = Modifier,
    recipe: Recipe
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .semantics { testTagsAsResourceId = true },
        verticalArrangement = Arrangement.Center
    ) {
        recipe.let {
            DetailHeader(recipe = it)
            Spacer(modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small)))
            DetailRecipeInfo(recipe = it)
            Spacer(modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small)))
            DetailRecipeIngredients(recipe = it)
            Spacer(modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small)))
            DetailRecipeInstructions(recipe = it)

        }
    }
}

@Preview
@Composable
fun RecipeDetailScreenPreview() {
    FlavorFiestaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RecipeDetailScreen(recipe = TestUtils.singleDummyRecipe)
        }
    }
}
