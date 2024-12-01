package com.mhss.app.shifak.presentation.common

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> AutoCompleteTextField(
    text: String,
    onTextChange: (String) -> Unit,
    onOptionSelected: (T) -> Unit,
    options: List<T>,
    optionName: (T) -> String,
    modifier: Modifier = Modifier
) {
    var exp by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(expanded = exp, onExpandedChange = { exp = !exp }) {
        MainTextField(
            value = text,
            onValueChange = {
                onTextChange(it)
                exp = true
            },
            hint = "",
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = exp)
            },
            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryEditable, true)
        )
        val filterOpts by remember(options, text) {
            derivedStateOf { options.filter { optionName(it).contains(text, ignoreCase = true) } }
        }
        if (filterOpts.isNotEmpty()) {
            ExposedDropdownMenu(expanded = exp, onDismissRequest = { exp = false }) {
                filterOpts.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(text = optionName(option))
                        },
                        onClick = {
                            onOptionSelected(option)
                            exp = false
                        }
                    )
                }
            }
        }
    }
}