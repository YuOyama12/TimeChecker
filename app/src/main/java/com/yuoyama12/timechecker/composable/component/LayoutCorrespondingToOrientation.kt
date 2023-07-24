package com.yuoyama12.timechecker.composable.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LayoutCorrespondingToOrientation(
    modifier: Modifier = Modifier,
    orientation: Int,
    content: @Composable () -> Unit
) {
    when (orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            Column(modifier) { content() }
        }
        Configuration.ORIENTATION_LANDSCAPE -> {
            Row(modifier) { content() }
        }
        else -> {
            Column(modifier) { content() }
        }
    }
}