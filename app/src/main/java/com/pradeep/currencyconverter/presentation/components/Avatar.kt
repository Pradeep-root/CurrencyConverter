package com.pradeep.currencyconverter.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dagger.Provides

@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    logoUrl: String
) {

    Card(
        modifier = modifier.size(60.dp),
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            AsyncImage(
                model = logoUrl,
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }
    }
}


@Preview
@Composable
fun AvatarPreview() {
    Avatar(
        logoUrl = "https://flagcdn.com/eu.svg"
    )
}