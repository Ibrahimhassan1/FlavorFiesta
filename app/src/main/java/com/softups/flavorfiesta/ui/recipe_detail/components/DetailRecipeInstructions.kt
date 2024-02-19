package com.softups.flavorfiesta.ui.recipe_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
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
fun DetailRecipeInstructions(
    modifier: Modifier = Modifier,
    recipe: Recipe
) {
    Card(
        modifier = modifier
            .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            .wrapContentHeight()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_small))
        ) {
            Text(
                text = stringResource(R.string.instructions),
                style = MaterialTheme.typography.headlineMedium,
                modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small))
            )
            FlowColumn {
                recipe.instructions.forEachIndexed { index, instruction ->
                    RecipeInstructionsItem(
                        modifier,
                        instruction = instruction,
                        stepNumber = index + 1
                    )
                }
            }
        }
    }
}

@Composable
fun RecipeInstructionsItem(modifier: Modifier = Modifier, instruction: String, stepNumber: Int) {
    Column(modifier, horizontalAlignment = Alignment.Start) {
        Text(
            text = stringResource(R.string.step_number, stepNumber),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = instruction,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small))
        )
    }
}

@Preview
@Composable
fun DetailRecipeInstructionsPreview() {
    FlavorFiestaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            DetailRecipeInstructions(recipe = TestUtils.dummyRecipesDto.toRecipes()[0])
        }
    }
}