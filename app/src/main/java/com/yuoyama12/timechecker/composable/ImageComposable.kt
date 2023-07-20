package com.yuoyama12.timechecker.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.yuoyama12.timechecker.R

@Composable
fun ResultImage(
    imageSize: Dp,
    isTrue: Boolean
) {
    if (isTrue) {
        Image(
            modifier = Modifier.size(imageSize),
            painter = painterResource(R.drawable.outline_circle_24),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.Red)
        )
    } else {
        Image(
            modifier = Modifier.size(imageSize),
            painter = painterResource(R.drawable.outline_cross_24),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.Blue)
        )
    }
}