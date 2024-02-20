package com.softups.flavorfiesta.ui.recipe_detail.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.softups.flavorfiesta.R
import com.softups.flavorfiesta.common.TestUtils
import com.softups.flavorfiesta.data.remote.dto.toRecipes
import com.softups.flavorfiesta.domain.model.Recipe
import com.softups.flavorfiesta.ui.theme.FlavorFiestaTheme

@Composable
fun DetailHeader(
    modifier: Modifier = Modifier, recipe: Recipe
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Column {
            Text(
                text = recipe.name,
                style = MaterialTheme.typography.headlineMedium,
                modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small))
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(recipe.image)
                    .crossfade(true).build(),
                contentDescription = null,
                placeholder = debugPlaceholder(R.drawable.top_app_bar_icon),
                modifier = modifier
                    .heightIn(125.dp)
                    .aspectRatio(1f)
                    .testTag(recipe.image),
                contentScale = ContentScale.Crop
            )
        }

    }
}

@Composable
fun debugPlaceholder(@DrawableRes debugPreview: Int) = if (LocalInspectionMode.current) {
    painterResource(id = debugPreview)
} else {
    null
}

@Preview
@Composable
fun DetailHeaderPreview() {
    FlavorFiestaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            DetailHeader(recipe = TestUtils.dummyRecipesDto.toRecipes()[0])
        }
    }
}