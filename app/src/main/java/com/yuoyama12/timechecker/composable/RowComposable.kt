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
import com.yuoyama12.timechecker.R

@Composable
fun RowWithSimpleHeader(
    modifier: Modifier = Modifier,
    header: String,
    headerModifier: Modifier = Modifier,
    headerFontSize: TextUnit = TextUnit.Unspecified,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = headerModifier
        ) {
            Text(
                modifier = Modifier
                    .border(1.dp, MaterialTheme.colorScheme.primary, RectangleShape)
                    .padding(horizontal = 6.dp),
                fontSize = headerFontSize,
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