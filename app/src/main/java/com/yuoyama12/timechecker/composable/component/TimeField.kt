package com.yuoyama12.timechecker.composable.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yuoyama12.timechecker.data.Time
import com.yuoyama12.timechecker.util.getHourList
import com.yuoyama12.timechecker.util.getMinuteList

private const val HOUR_MINUTE_DIVIDE_TEXT = ":"
@Composable
fun TimeField(
    time: Time,
    onHourChanged: (hour: String) -> Unit,
    onMinuteChanged: (minute: String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        HourField(
            hour = time.hour,
            onHourChanged = { hour -> onHourChanged(hour) }
        )

        Text(
            modifier = Modifier.padding(horizontal = 4.dp),
            fontWeight = FontWeight.Bold,
            text = HOUR_MINUTE_DIVIDE_TEXT
        )

        MinuteField(
            minute = time.minute,
            onMinuteChanged = { minute -> onMinuteChanged(minute) }
        )
    }
}

@Composable
private fun HourField(
    hour: String,
    onHourChanged: (hour: String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ButtonForDropdownMenu(
        items = getHourList(),
        currentItem = hour,
        expanded = expanded,
        onButtonClicked = { expanded = !expanded },
        onItemClicked = { _hour -> onHourChanged(_hour) },
        onDismissRequest = { expanded = false }
    )
}

@Composable
private fun MinuteField(
    minute: String,
    onMinuteChanged: (minute: String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ButtonForDropdownMenu(
        items = getMinuteList(),
        currentItem = minute,
        expanded = expanded,
        onButtonClicked = { expanded = !expanded },
        onItemClicked = { _minute -> onMinuteChanged(_minute) },
        onDismissRequest = { expanded = false }
    )
}

@Composable
private fun <E> ButtonForDropdownMenu(
    items: List<E>,
    currentItem: E,
    expanded: Boolean,
    onButtonClicked: () -> Unit,
    onDismissRequest: () -> Unit,
    onItemClicked: (E) -> Unit
) {
    TextButton(
        onClick = onButtonClicked,
        modifier = Modifier.border(BorderStroke(0.5.dp, MaterialTheme.colorScheme.primary)),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = currentItem.toString(),
                textAlign = TextAlign.Center,
                maxLines = 1
            )

            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismissRequest
        ) {
            for (i in items) {
                DropdownMenuItem(
                    text = { Text(text = i.toString()) },
                    onClick = {
                        onItemClicked(i)
                        onDismissRequest()
                    }
                )

                Divider()
            }
        }
    }
}