package com.yuoyama12.timechecker.composable.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yuoyama12.timechecker.R
import com.yuoyama12.timechecker.composable.RowOfCheckResultImageAndText
import com.yuoyama12.timechecker.data.CheckResult

private val timeTextPadding = 10.dp
@Composable
fun CheckResultListItem(
    modifier: Modifier = Modifier,
    checkResult: CheckResult
) {
    Column(modifier) {
        Row(
            modifier = Modifier.padding(all = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(all = 8.dp)
            ) {
                Text(
                    modifier = Modifier.padding(all = timeTextPadding),
                    text = checkResult.selectedTime.toTimeFormat(),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier.padding(horizontal = timeTextPadding.plus(8.dp)),
                    text = stringResource(
                        R.string.list_item_time_range_text,
                        checkResult.startTime.toTimeFormat(),
                        checkResult.endTime.toTimeFormat()
                    )
                )
            }

            RowOfCheckResultImageAndText(
                imageSize = 40.dp,
                textFontSize = 28.sp,
                isTrue = checkResult.isIncluded
            )
        }

        Divider()
    }
}