package com.softups.flavorfiesta.ui.recipe_list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import com.softups.flavorfiesta.R

@Composable
fun DisplayMessage(
    messageText: String,
    modifier: Modifier
) {
    Text(
        text = messageText,
        style = MaterialTheme.typography.displayMedium,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_medium))
    )
}