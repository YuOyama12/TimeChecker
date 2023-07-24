package com.yuoyama12.timechecker.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yuoyama12.timechecker.R
import com.yuoyama12.timechecker.ui.theme.crossIconColor

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
            colorFilter = ColorFilter.tint(crossIconColor())
        )
    }
}

@Composable
fun NoListItemImage(
    modifier: Modifier = Modifier,
    image: Painter,
    textBelowImage: String = "",
    color: Color
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(96.dp),
                painter = image,
                contentDescription = null,
                colorFilter = ColorFilter.tint(color)
            )

            if (textBelowImage != "") {
                Text(
                    text = textBelowImage,
                    fontSize = 24.sp,
                    color = color
                )
            }
        }
    }
}