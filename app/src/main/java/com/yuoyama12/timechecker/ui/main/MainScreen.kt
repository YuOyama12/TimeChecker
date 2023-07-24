package com.yuoyama12.timechecker.ui.main

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yuoyama12.timechecker.R
import com.yuoyama12.timechecker.composable.MainButton
import com.yuoyama12.timechecker.composable.RowOfCheckResultImageAndText
import com.yuoyama12.timechecker.composable.RowWithSimpleHeader
import com.yuoyama12.timechecker.composable.component.LayoutCorrespondingToOrientation
import com.yuoyama12.timechecker.composable.component.TimeField
import com.yuoyama12.timechecker.data.CheckResult
import com.yuoyama12.timechecker.data.Time

private val timeHeaderModifier = Modifier.padding(horizontal = 16.dp)
private val timeRangeRowPadding = 12.dp
private val timeRangeHeaderFontSize = 16.sp
private val resultTextFontSize = 30.sp
@Composable
fun MainScreen(
    navigateToResultListScreen: () -> Unit
) {
    val context = LocalContext.current

    val configuration = LocalConfiguration.current
    var orientation by remember { mutableStateOf(Configuration.ORIENTATION_PORTRAIT) }

    val viewModel: MainViewModel = hiltViewModel()

    var isIncluded: Boolean? by rememberSaveable { mutableStateOf(null) }
    var selectedTime by rememberSaveable { mutableStateOf(Time()) }
    var startTime by rememberSaveable { mutableStateOf(Time()) }
    var endTime by rememberSaveable { mutableStateOf(Time()) }

    val scrollState = rememberScrollState()

    val messageOnResultInserted = stringResource(R.string.result_inserted_message)


    LaunchedEffect(configuration) {
        snapshotFlow { configuration.orientation }
            .collect { orientation = it }

        scrollState.animateScrollTo(0)
    }

    LaunchedEffect(isIncluded) {
        if (isIncluded != null) {
            scrollState.animateScrollTo(scrollState.maxValue)
        }
    }

    Box(modifier = Modifier.verticalScroll(scrollState)) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 6.dp),
                fontSize = 12.sp,
                text = stringResource(R.string.app_description)
            )

            RowWithSimpleHeader(
                modifier = Modifier.padding(vertical = 10.dp),
                headerModifier = timeHeaderModifier,
                header = stringResource(R.string.input_time_title),
                headerFontSize = timeRangeHeaderFontSize
            ) {
                TimeField(
                    time = selectedTime,
                    onHourChanged = { hour ->
                        selectedTime = selectedTime.copy(hour = hour)
                    },
                    onMinuteChanged = { minute ->
                        selectedTime = selectedTime.copy(minute = minute)
                    }
                )
            }

            Column(
                modifier = Modifier.padding(vertical = 6.dp)
            ) {
                Text(text = stringResource(R.string.select_range_title))

                LayoutCorrespondingToOrientation(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.6.dp, MaterialTheme.colorScheme.secondary, RectangleShape),
                    orientation = orientation
                ) {
                    RowWithSimpleHeader(
                        modifier = Modifier.padding(all = timeRangeRowPadding),
                        headerModifier = timeHeaderModifier,
                        header = stringResource(R.string.start_time_title),
                        headerFontSize = timeRangeHeaderFontSize
                    ) {
                        TimeField(
                            time = startTime,
                            onHourChanged = { hour ->
                                startTime = startTime.copy(hour = hour)
                            },
                            onMinuteChanged = { minute ->
                                startTime = startTime.copy(minute = minute)
                            }
                        )
                    }

                    RowWithSimpleHeader(
                        modifier = Modifier.padding(all = timeRangeRowPadding),
                        headerModifier = timeHeaderModifier,
                        header = stringResource(R.string.end_time_title),
                        headerFontSize = timeRangeHeaderFontSize
                    ) {
                        TimeField(
                            time = endTime,
                            onHourChanged = { hour ->
                                endTime = endTime.copy(hour = hour)
                            },
                            onMinuteChanged = { minute ->
                                endTime = endTime.copy(minute = minute)
                            }
                        )
                    }
                }
            }

            MainButton(
                modifier = Modifier.padding(vertical = 6.dp),
                onClick = {
                    if (viewModel.isSameToPreviousResult(selectedTime, startTime, endTime))
                        return@MainButton

                    //同値が入力されても、画面の更新処理を走らせるために一度変数を初期化。
                    isIncluded = null
                    isIncluded = viewModel.checkIfTimeIsIncludedInRange(selectedTime, startTime, endTime)

                    if (isIncluded != null) {
                        val checkResult = CheckResult(
                            selectedTime = selectedTime,
                            startTime = startTime,
                            endTime = endTime,
                            isIncluded = isIncluded!!
                        )
                        viewModel.insertCheckResult(checkResult)
                        showToast(context, messageOnResultInserted)
                    }
                },
                buttonText = stringResource(R.string.check_button_text)
            )

            MainButton(
                onClick = {
                    navigateToResultListScreen()
                },
                buttonText = stringResource(R.string.result_history_button_text)
            )

            Column(
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 24.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.result_title),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 100.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    if (isIncluded != null) {
                        RowOfCheckResultImageAndText(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            imageSize = 82.dp,
                            textFontSize = resultTextFontSize,
                            isTrue = isIncluded!!
                        )
                    } else {
                        Text(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = stringResource(R.string.no_result_text),
                            fontSize = resultTextFontSize
                        )
                    }
                }
            }
        }
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}