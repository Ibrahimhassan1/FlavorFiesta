package com.softups.flavorfiesta.ui.recipe_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.softups.flavorfiesta.R
import com.softups.flavorfiesta.common.TestUtils
import com.softups.flavorfiesta.data.remote.dto.toRecipes
import com.softups.flavorfiesta.domain.model.Recipe
import com.softups.flavorfiesta.ui.theme.FlavorFiestaTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RecipeListItem(
    modifier: Modifier = Modifier,
    recipe: Recipe,
    onItemClick: (Recipe) -> Unit
) {
    Card(
        modifier = modifier
            .clickable {
                onItemClick(recipe)
            }
            .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            .wrapContentHeight()
            .semantics { testTagsAsResourceId = true }
    ) {
        Row {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(recipe.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
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
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_small)
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Timelapse,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = dimensionResource(R.dimen.padding_medium))
                    )
                    Text(
                        text = stringResource(
                            R.string.time_in_minutes,
                            recipe.prepTimeMinutes
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(start = dimensionResource(R.dimen.padding_small))
                            .testTag("${recipe.id} ${stringResource(R.string.preparation_time_in_minutes_test_tag)}")
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun RecipeListScreenPreview() {
    FlavorFiestaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RecipeListItem(
                recipe = TestUtils.dummyRecipesDto.toRecipes()[0],
                onItemClick = {}
            )
        }
    }
}