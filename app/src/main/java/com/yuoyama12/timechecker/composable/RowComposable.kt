package com.yuoyama12.timechecker.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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