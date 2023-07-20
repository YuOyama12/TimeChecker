package com.yuoyama12.timechecker.ui.main

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yuoyama12.timechecker.R
import com.yuoyama12.timechecker.composable.MainButton
import com.yuoyama12.timechecker.composable.RowOfCheckResultImageAndText
import com.yuoyama12.timechecker.composable.RowWithSimpleHeader
import com.yuoyama12.timechecker.composable.component.TimeField
import com.yuoyama12.timechecker.data.Time

private val timeFieldSpacerModifier = Modifier.padding(vertical = 6.dp)
private val resultTextFontSize = 30.sp
@Composable
fun MainScreen(
    navigateToResultListScreen: () -> Unit
) {
    val viewModel: MainViewModel = hiltViewModel()
    val checkResult by viewModel.checkResult.collectAsState()

    var selectedTime by rememberSaveable { mutableStateOf(Time()) }
    var startTime by rememberSaveable { mutableStateOf(Time()) }
    var endTime by rememberSaveable { mutableStateOf(Time()) }

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
            header = stringResource(R.string.input_time_title)
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.6.dp, MaterialTheme.colorScheme.secondary, RectangleShape)
            ) {
                Spacer(modifier = timeFieldSpacerModifier)

                RowWithSimpleHeader(
                    header = stringResource(R.string.start_time_title)
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

                Spacer(modifier = timeFieldSpacerModifier)

                RowWithSimpleHeader(
                    header = stringResource(R.string.end_time_title)
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

                Spacer(modifier = timeFieldSpacerModifier)
            }
        }

        MainButton(
            modifier = Modifier.padding(vertical = 6.dp),
            onClick = {
                viewModel.checkIfTimeIsInRange(selectedTime, startTime, endTime)
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

            if (checkResult != null) {
                RowOfCheckResultImageAndText(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    imageSize = 82.dp,
                    textFontSize = resultTextFontSize,
                    isTrue = checkResult!!
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