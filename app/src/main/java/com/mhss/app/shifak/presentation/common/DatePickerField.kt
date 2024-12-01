package com.mhss.app.shifak.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mhss.app.shifak.R
import com.mhss.app.shifak.util.formatDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerField(
    state: DatePickerState,
    hint: String,
    modifier: Modifier = Modifier,
    showDay: Boolean = false
) {
    var showDatePicker by rememberSaveable { mutableStateOf(false) }
    val selectedDate = state.selectedDateMillis?.formatDate(showDay) ?: ""

    Box(modifier = modifier.fillMaxWidth()) {
        MainTextField(
            value = selectedDate,
            onValueChange = { },
            hint = hint,
            enabled = false,
            modifier = Modifier.fillMaxWidth().clickable { showDatePicker = true }
        )

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false},
                confirmButton = {
                    TextButton(onClick = {
                       showDatePicker = false
                    }) {
                        Text(stringResource(R.string.save))
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showDatePicker = false
                    }) {
                        Text(stringResource(R.string.cancel))
                    }
                }
            ) {
                DatePicker(state = state)
            }
        }
    }
}