package com.yuoyama12.timechecker.composable

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.yuoyama12.timechecker.R

@Composable
fun ConfirmationDialog(
    title: String,
    message: String? = null,
    positiveButtonText: String,
    onDismissRequest: () -> Unit,
    onPositiveClicked: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        title = { Text(text = title) },
        text = { Text(text = message ?: "") },
        confirmButton = {
            TextButton(
                onClick = {
                    onPositiveClicked()
                    onDismissRequest()
                }
            ) {
                Text(text = positiveButtonText)
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onDismissRequest() }
            ) {
                Text(text = stringResource(R.string.dialog_cancel))
            }
        }

    )
}