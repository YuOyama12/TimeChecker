package com.yuoyama12.timechecker.ui.resultlist

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.yuoyama12.timechecker.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultListScreen(
    onBackArrowClick: () -> Unit
) {
    val viewModel: ResultListViewModel = hiltViewModel()
    val resultList by viewModel.resultList.collectAsState(emptyList())

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
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { padding ->
      LazyColumn(
          modifier = Modifier.padding(padding)
      ) {
          items(resultList) { result ->
              Text(text = result.toString())
          }
      }
    }
}