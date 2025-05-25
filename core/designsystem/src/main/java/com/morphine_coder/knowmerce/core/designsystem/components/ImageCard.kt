package com.morphine_coder.knowmerce.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

/**
 * Create by jaehyeon.
 * Date: 2025. 5. 25.
 */
@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    tileStamp: String,
    isSavedImage: Boolean? = false,
    onClick: () -> Unit = { },
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardColors(
            contentColor = Color.White,
            containerColor = Color.White,
            disabledContentColor = Color.Gray.copy(alpha = 0.5f),
            disabledContainerColor = Color.LightGray.copy(alpha = 0.5f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(color = Color.LightGray),
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Fit
                )

                if (isSavedImage != null) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Saved Icon",
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp),
                        tint = if (isSavedImage) Color.Yellow else Color.Gray
                    )
                }
            } // Image section

            Text(
                text = tileStamp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                color = Color.Black,
                maxLines = 1,
                softWrap = true
            )
        }
    }
}

@Preview
@Composable
private fun ImageCardPreview() {
    ImageCard(
        imageUrl = "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcGbz7k%2FbtsD1mY2YBd%2FLkWiVVFa4fwyHiCkSW0Ru0%2Fimg.png",
        tileStamp = "Sample Tile",
        isSavedImage = false,
        onClick = { /* Handle click */ }
    )
}