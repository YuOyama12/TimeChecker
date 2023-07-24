package com.yuoyama12.timechecker.ui.resultlist

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.yuoyama12.timechecker.R
import com.yuoyama12.timechecker.composable.ConfirmationDialog
import com.yuoyama12.timechecker.composable.NoListItemImage
import com.yuoyama12.timechecker.composable.component.CheckResultListItem
import com.yuoyama12.timechecker.data.CheckResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultListScreen(
    onBackArrowClick: () -> Unit
) {
    val context = LocalContext.current

    val viewModel: ResultListViewModel = hiltViewModel()
    val resultList by viewModel.resultList.collectAsState(emptyList())

    var openClearListConfirmationDialog by remember { mutableStateOf(false) }
    var openDeleteItemConfirmationDialog by remember { mutableStateOf(false) }

    val messageOnListCleared = stringResource(R.string.list_item_all_cleared_message)
    val messageOnItemDeleted = stringResource(R.string.list_item_deleted_message)
    val messageOnItemDeletedFailed = stringResource(R.string.list_item_deleted_failed_message)


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.result_list_app_bar_title),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onBackArrowClick() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { openClearListConfirmationDialog = true }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { padding ->
        var selected: CheckResult? by remember { mutableStateOf(null) }

        if (resultList.isEmpty()) {
            NoListItemImage(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                image = painterResource(R.drawable.baseline_history_24),
                textBelowImage = stringResource(R.string.no_result_history_text),
                color = MaterialTheme.colorScheme.secondary
            )
        } else {
            LazyColumn(
                modifier = Modifier.padding(padding)
            ) {
                items(
                    items = resultList,
                    key = { it.id },
                ) { result ->
                    CheckResultListItem(
                        checkResult = result,
                        onLongClick = {
                            selected = it
                            openDeleteItemConfirmationDialog = true
                        }
                    )
                }
            }
        }

        if (openDeleteItemConfirmationDialog) {
            ConfirmationDialog(
                title = stringResource(R.string.delete_item_confirmation_dialog_title),
                message = stringResource(R.string.delete_item_confirmation_dialog_message),
                positiveButtonText = stringResource(R.string.delete_confirmation_dialog_positive_button_text),
                onDismissRequest = { openDeleteItemConfirmationDialog = false },
                onPositiveClicked = {
                    if (selected != null) {
                        viewModel.deleteItem(selected!!)
                        showToast(context, messageOnItemDeleted)
                    } else {
                        showToast(context, messageOnItemDeletedFailed)
                    }
                }
            )
        }
    }

    if (openClearListConfirmationDialog) {
        ConfirmationDialog(
            title = stringResource(R.string.clear_list_confirmation_dialog_title),
            message = stringResource(R.string.clear_list_confirmation_dialog_message),
            positiveButtonText = stringResource(R.string.delete_confirmation_dialog_positive_button_text),
            onDismissRequest = { openClearListConfirmationDialog = false },
            onPositiveClicked = {
                viewModel.clearList()
                showToast(context, messageOnListCleared)
            }
        )
    }

}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}