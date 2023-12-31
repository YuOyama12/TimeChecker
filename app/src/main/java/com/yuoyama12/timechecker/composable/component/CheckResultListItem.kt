package com.yuoyama12.timechecker.composable.component

import android.content.res.Configuration
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yuoyama12.timechecker.R
import com.yuoyama12.timechecker.composable.RowOfCheckResultImageAndText
import com.yuoyama12.timechecker.composable.RowWithSimpleHeader
import com.yuoyama12.timechecker.data.CheckResult

private val timeTextPadding = 8.dp
private val timeRangeTextPadding = timeTextPadding.plus(6.dp)
@Composable
fun CheckResultListItem(
    modifier: Modifier = Modifier,
    orientation: Int,
    checkResult: CheckResult,
    onLongClick: (CheckResult) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
            .indication(interactionSource, LocalIndication.current)
            .pointerInput(true) {
                detectTapGestures(
                    onPress = {
                        val press = PressInteraction.Press(it)
                        interactionSource.emit(press)
                        tryAwaitRelease()
                        interactionSource.emit(PressInteraction.Release(press))
                    },
                    onLongPress = { onLongClick(checkResult) }
                )
            }
    ) {
        Row(
            modifier = Modifier.padding(all = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LayoutCorrespondingToOrientation(
                modifier = Modifier.padding(all = 8.dp),
                orientation = orientation
            ) {
                Text(
                    modifier = Modifier.padding(all = timeTextPadding),
                    text = checkResult.selectedTime.toTimeFormat(),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                RowWithSimpleHeader(
                    modifier = when(orientation) {
                        Configuration.ORIENTATION_PORTRAIT -> Modifier.padding(horizontal = timeRangeTextPadding)
                        Configuration.ORIENTATION_LANDSCAPE -> Modifier.padding(all = timeRangeTextPadding)
                        else -> Modifier.padding(horizontal = timeRangeTextPadding)
                    },
                    header = stringResource(R.string.list_item_time_range_title),
                    headerModifier = Modifier.padding(horizontal = 6.dp)
                ) {
                    Text(
                        text = stringResource(
                            R.string.list_item_time_range_text,
                            checkResult.startTime.toTimeFormat(),
                            checkResult.endTime.toTimeFormat()
                        )
                    )
                }
            }

            RowOfCheckResultImageAndText(
                imageSize = 36.dp,
                textFontSize = 24.sp,
                isTrue = checkResult.isIncluded
            )
        }

        Divider()
    }
}