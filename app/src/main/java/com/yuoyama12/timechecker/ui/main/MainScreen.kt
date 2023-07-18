package com.yuoyama12.timechecker.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yuoyama12.timechecker.R

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 16.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 6.dp),
            text = stringResource(R.string.app_description)
        )
    }
}