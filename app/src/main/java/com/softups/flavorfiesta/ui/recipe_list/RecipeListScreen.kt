package com.softups.flavorfiesta.ui.recipe_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.softups.flavorfiesta.R
import com.softups.flavorfiesta.common.Constants.UN_EXPECTED_ERROR
import com.softups.flavorfiesta.common.TestUtils
import com.softups.flavorfiesta.data.remote.dto.toRecipes
import com.softups.flavorfiesta.domain.model.Recipe
import com.softups.flavorfiesta.ui.recipe_list.components.RecipeListItem
import com.softups.flavorfiesta.ui.theme.FlavorFiestaTheme

@Composable
fun RecipeListScreen(
    modifier: Modifier = Modifier,
    recipeListState: RecipeListState,
    onItemClick: (Recipe) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
            modifier = modifier
        ) {
            items(recipeListState.recipes) { recipe ->
                RecipeListItem(modifier, recipe, onItemClick)
            }
        }
        if (recipeListState.error.isNotBlank()) {
            Text(
                text = recipeListState.error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_medium))
                    .align(Alignment.Center)
            )
        }
        if (recipeListState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeListScreenPreviewError() {
    FlavorFiestaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RecipeListScreen(
                recipeListState = RecipeListState(
                    isLoading = false,
                    error = UN_EXPECTED_ERROR
                ),
                onItemClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeListScreenPreviewLoading() {
    FlavorFiestaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RecipeListScreen(
                recipeListState = RecipeListState(
                    isLoading = true
                ),
                onItemClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeListScreenPreviewData() {
    FlavorFiestaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RecipeListScreen(
                recipeListState = RecipeListState(
                    recipes = TestUtils.dummyRecipesDto.toRecipes()
                ),
                onItemClick = {}
            )
        }
    }
}