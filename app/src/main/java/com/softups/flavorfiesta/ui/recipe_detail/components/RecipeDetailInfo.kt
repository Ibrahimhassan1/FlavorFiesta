package com.softups.flavorfiesta.ui.recipe_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.softups.flavorfiesta.R
import com.softups.flavorfiesta.common.TestUtils
import com.softups.flavorfiesta.data.remote.dto.toRecipes
import com.softups.flavorfiesta.domain.model.Recipe
import com.softups.flavorfiesta.ui.theme.FlavorFiestaTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailRecipeInfo(
    modifier: Modifier = Modifier,
    recipe: Recipe
) {
    Card(
        modifier = modifier
            .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            .wrapContentHeight(),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_small)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FlowRow(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                RecipeFact(
                    modifier = modifier,
                    title = stringResource(R.string.prep_time),
                    value = stringResource(
                        R.string.time_in_minutes,
                        recipe.prepTimeMinutes
                    )
                )
                RecipeFact(
                    modifier = modifier,
                    title = stringResource(R.string.cook_time),
                    value = stringResource(
                        R.string.time_in_minutes,
                        recipe.cookTimeMinutes
                    )
                )
                RecipeFact(
                    modifier = modifier,
                    title = stringResource(R.string.servings),
                    value = recipe.servings.toString()
                )
                RecipeFact(
                    modifier = modifier,
                    title = stringResource(R.string.calories_per_servings),
                    value = recipe.caloriesPerServing.toString()
                )
            }

        }
    }
}

@Composable
fun RecipeFact(modifier: Modifier = Modifier, title: String, value: String) {
    Row(modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .padding(end = dimensionResource(id = R.dimen.padding_small))
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier
                .padding(end = dimensionResource(id = R.dimen.padding_medium))
                .align(Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
fun DetailRecipeInfoPreview() {
    FlavorFiestaTheme(dynamicColor = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            DetailRecipeInfo(recipe = TestUtils.dummyRecipesDto.toRecipes()[0])
        }
    }
}