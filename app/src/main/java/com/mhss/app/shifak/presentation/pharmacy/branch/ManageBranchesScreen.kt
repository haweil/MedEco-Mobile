package com.mhss.app.shifak.presentation.pharmacy.branch

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mhss.app.shifak.R
import com.mhss.app.shifak.data.remote.pharmacy.model.CreatePharmacyBranchBody
import com.mhss.app.shifak.domain.model.drug.Drug
import com.mhss.app.shifak.domain.model.pharmacy.PharmacyBranch
import com.mhss.app.shifak.presentation.common.AutoCompleteTextField
import com.mhss.app.shifak.presentation.common.MainTextField
import com.mhss.app.shifak.presentation.common.MainTopAppBar
import com.mhss.app.shifak.presentation.common.Screen
import com.mhss.app.shifak.presentation.common.SmallOutlinedButton

@Composable
fun ManageBranchesScreen(
    state: ManageBranchesUiState,
    pharmacyId: Int,
    onEvent: (ManageBranchesEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    var showAddDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    LaunchedEffect(state) {
        if (!state.loading) {
            showAddDialog = false
        }
        if (state.error != null) {
            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
        }
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            MainTopAppBar(
                title = stringResource(R.string.manage_branches),
                onNavigateUp = {}
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(12.dp)
                .padding(paddingValues)
        ) {
            Card(
                onClick = { showAddDialog = true },
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = stringResource(R.string.add_branch),
                        style = MaterialTheme.typography.titleMedium
                    )

                }
            }
        }
    }
    if (showAddDialog) {
        AddBranchDialog(
            onDismiss = { showAddDialog = false }
        ) {
            onEvent(ManageBranchesEvent.AddBranch(
                it.copy(pharmacyId = pharmacyId.toString())
            ))
        }
    }
}

@Composable
fun AddBranchDialog(
    onDismiss: () -> Unit,
    onAdd: (CreatePharmacyBranchBody) -> Unit,
) {
    var address by remember {
        mutableStateOf("")
    }
    var phone by remember {
        mutableStateOf("")
    }
    var lat by remember {
        mutableStateOf("")
    }
    var lng by remember {
        mutableStateOf("")
    }
    var crn by remember {
        mutableStateOf("")
    }

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(8.dp))
                MainTextField(
                    value = address,
                    onValueChange = { address = it },
                    hint = stringResource(R.string.address),
                    keyboardType = KeyboardType.Text,
                )
                Spacer(Modifier.height(8.dp))
                MainTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    hint = stringResource(R.string.phone_number),
                    keyboardType = KeyboardType.Phone,
                )
                Spacer(Modifier.height(8.dp))
                MainTextField(
                    value = crn,
                    onValueChange = { crn = it },
                    hint = stringResource(R.string.commercial_number),
                    keyboardType = KeyboardType.Text,
                )
                Spacer(Modifier.height(8.dp))
                MainTextField(
                    value = lat,
                    onValueChange = { lat = it },
                    hint = "lat",
                    keyboardType = KeyboardType.Decimal,
                )
                Spacer(Modifier.height(8.dp))
                MainTextField(
                    value = lng,
                    onValueChange = { lng = it },
                    hint = "lng",
                    keyboardType = KeyboardType.Decimal,
                )
                Spacer(Modifier.height(16.dp))
                SmallOutlinedButton(
                    text = stringResource(R.string.add_branch)
                ) {
                    onAdd(
                        CreatePharmacyBranchBody(
                            address = address,
                            phone = phone,
                            lat = lat.toDoubleOrNull() ?: 0.0,
                            lng = lng.toDoubleOrNull() ?: 0.0,
                            commercialRegistrationNumber = crn,
                            taxNumber = crn,
                            pharmacyId = ""
                        )
                    )
                    onDismiss()
                }
            }
        }
    }
}
