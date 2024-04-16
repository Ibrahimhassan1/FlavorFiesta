package com.softups.flavorfiesta.ui.recipe_detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.softups.flavorfiesta.R
import com.softups.flavorfiesta.common.TestUtils
import com.softups.flavorfiesta.data.remote.dto.toRecipes
import com.softups.flavorfiesta.ui.recipe_detail.RecipeDetailsState
import com.softups.flavorfiesta.ui.theme.FlavorFiestaTheme

@Composable
fun RecipeDetailScreenLandscape(
    modifier: Modifier = Modifier,
    recipeDetailsState: RecipeDetailsState,
) {
    recipeDetailsState.selectedRecipe?.let { recipe ->
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Card(
                modifier = modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
                    .wrapContentHeight(),
            ) {
                Row {
                    Box {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(recipe.image)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            placeholder = debugPlaceholder(R.drawable.top_app_bar_icon),
                            modifier = modifier
                                .size(width = 125.dp, height = 125.dp)
                                .aspectRatio(1f)
                                .testTag(recipe.image),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Column {
                        Text(
                            text = recipe.name,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = modifier.padding(
                                start = dimensionResource(R.dimen.padding_medium),
                                top = dimensionResource(R.dimen.padding_medium),
                                end = dimensionResource(R.dimen.padding_medium),
                                bottom = dimensionResource(R.dimen.padding_small)
                            )
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            DetailRecipeInfo(modifier, recipe)
                        }
                    }
                }
            }
            Spacer(modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small)))
            Row {
                DetailRecipeInstructions(recipe = recipe, modifier = modifier)
                DetailRecipeIngredients(recipe = recipe, modifier = modifier)
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 840, heightDp = 480)
@Composable
fun RecipeDetailLandscapePreview() {
    FlavorFiestaTheme(dynamicColor = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RecipeDetailScreenLandscape(recipeDetailsState = RecipeDetailsState(selectedRecipe = TestUtils.dummyRecipesDto.toRecipes()[0]))
        }
    }
}