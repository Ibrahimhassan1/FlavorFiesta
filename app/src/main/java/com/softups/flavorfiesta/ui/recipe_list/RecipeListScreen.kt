package com.softups.flavorfiesta.ui.recipe_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.softups.flavorfiesta.R
import com.softups.flavorfiesta.common.Constants.UN_EXPECTED_ERROR
import com.softups.flavorfiesta.common.TestUtils
import com.softups.flavorfiesta.data.remote.dto.toRecipes
import com.softups.flavorfiesta.domain.model.Recipe
import com.softups.flavorfiesta.ui.recipe_list.components.DisplayError
import com.softups.flavorfiesta.ui.recipe_list.components.DisplayMessage
import com.softups.flavorfiesta.ui.recipe_list.components.RecipeListItem
import com.softups.flavorfiesta.ui.theme.FlavorFiestaTheme

@Composable
fun RecipeListScreen(
    modifier: Modifier = Modifier,
    recipeListState: RecipeListState,
    onItemClick: (Recipe) -> Unit,
    onRefreshClick: () -> Unit,
    widthSizeClass: WindowWidthSizeClass = WindowWidthSizeClass.Compact
) {
    val context = LocalContext.current
    Box(modifier = modifier.fillMaxSize()) {
        when {
            recipeListState.recipes.isNotEmpty() -> {
                val gridRowCount = remember {
                    // When orientation is Portrait
                    when (widthSizeClass) {
                        WindowWidthSizeClass.Compact -> {
                            1
                        }
                        // Other wise
                        else -> {
                            2
                        }
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(gridRowCount),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
                    modifier = modifier
                ) {
                    items(recipeListState.recipes) { recipe ->
                        RecipeListItem(modifier, recipe, onItemClick)
                    }
                }
            }

            recipeListState.error.isBlank() && !recipeListState.isLoading -> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    DisplayMessage(
                        messageText = stringResource(id = R.string.empty_list),
                        modifier = modifier.align(Alignment.CenterHorizontally)
                    )
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refresh Icon",
                        modifier = Modifier
                            .clickable {
                                onRefreshClick()
                            }
                    )
                }
            }

            recipeListState.error.isNotBlank() -> {
                DisplayError(recipeListState.error, modifier.align(Alignment.Center))
            }

            recipeListState.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .semantics {
                            contentDescription =
                                context.getString(R.string.loading_recipes_content_description)
                        },

                    )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeListScreenPreviewError() {
    FlavorFiestaTheme(dynamicColor = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RecipeListScreen(
                recipeListState = RecipeListState(
                    isLoading = false,
                    error = UN_EXPECTED_ERROR
                ),
                onItemClick = {},
                onRefreshClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeListScreenPreviewEmpty() {
    FlavorFiestaTheme(dynamicColor = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RecipeListScreen(
                recipeListState = RecipeListState(
                    isLoading = false,
                    error = "",
                    recipes = emptyList()
                ),
                onItemClick = {},
                onRefreshClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeListScreenPreviewLoading() {
    FlavorFiestaTheme(dynamicColor = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RecipeListScreen(
                recipeListState = RecipeListState(
                    isLoading = true
                ),
                onItemClick = {},
                onRefreshClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeListScreenPreviewData() {
    FlavorFiestaTheme(dynamicColor = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RecipeListScreen(
                recipeListState = RecipeListState(
                    recipes = TestUtils.dummyRecipesDto.toRecipes()
                ),
                onItemClick = {},
                onRefreshClick = {}
            )
        }
    }
}