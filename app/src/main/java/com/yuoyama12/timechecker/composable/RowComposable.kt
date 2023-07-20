package com.yuoyama12.timechecker.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yuoyama12.timechecker.R

private val titleFontSize = 16.sp
@Composable
fun RowWithSimpleHeader(
    modifier: Modifier = Modifier,
    header: String,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                modifier = Modifier
                    .border(1.dp, MaterialTheme.colorScheme.primary, RectangleShape)
                    .padding(horizontal = 6.dp),
                fontSize = titleFontSize,
                text = header
            )
        }

        content()
    }

}

@Composable
fun RowOfCheckResultImageAndText(
    modifier: Modifier = Modifier,
    imageSize: Dp,
    textFontSize: TextUnit,
    isTrue: Boolean
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ResultImage(
            imageSize = imageSize,
            isTrue = isTrue
        )

        Spacer(modifier = Modifier.padding(horizontal = 4.dp))

        Text(
            text = if (isTrue) stringResource(R.string.result_include_text)
                    else stringResource(R.string.result_not_include_text),
            fontSize = textFontSize
        )
    }
}