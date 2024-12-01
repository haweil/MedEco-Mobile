package com.mhss.app.shifak.presentation.user.donate

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mhss.app.shifak.R
import com.mhss.app.shifak.domain.model.drug.Drug
import com.mhss.app.shifak.presentation.common.AutoCompleteTextField
import com.mhss.app.shifak.presentation.common.MainButton
import com.mhss.app.shifak.presentation.common.MainTextField
import com.mhss.app.shifak.presentation.common.MainTopAppBar
import com.mhss.app.shifak.presentation.common.SmallOutlinedButton
import com.mhss.app.shifak.presentation.ui.theme.MedEcoTheme
import com.mhss.app.shifak.presentation.user.donate.components.DrugCard
import com.mhss.app.shifak.presentation.user.donate.donation_data.DonationEvent
import com.mhss.app.shifak.presentation.user.donate.donation_data.DonationUiState


@SuppressLint("DefaultLocale")
@Composable
fun DonationDataScreen(
    state: DonationUiState,
    branchId: Int,
    pharmacyName: String,
    onEvent: (DonationEvent) -> Unit,
) {
    val selectedDrugs = remember {
        mutableStateMapOf<Drug, Int>()
    }
    var points by remember {
        mutableIntStateOf(0)
    }
    val context = LocalContext.current
    var showAddDialog by remember { mutableStateOf(false) }
    var showOrderIdDialog by remember { mutableStateOf(false) }
    LaunchedEffect(selectedDrugs.size) {
        var sum = 0
        selectedDrugs.forEach { (drug, qty) ->
            sum += drug.points * qty
        }
        points = sum
    }
    LaunchedEffect(state) {
        if (state.orderId != null) {
            showAddDialog = false
            showOrderIdDialog = true
        }
        if (state.error != null) {
            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
        }
    }
    Scaffold(
        topBar = {
            MainTopAppBar(
                title = stringResource(R.string.donation_data_title),
                onNavigateUp = {}
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(12.dp)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(8.dp))
            for ((drug, qty) in selectedDrugs) {
                DrugCard(drug.name, qty)
                Spacer(Modifier.height(8.dp))
            }
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
                        text = stringResource(R.string.add_drug),
                        style = MaterialTheme.typography.titleMedium
                    )

                }
            }
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier.padding(2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_gift),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = stringResource(id = R.string.estimated_points) + ":",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.primary,
                    ),
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = String.format("%,d", points),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }
            Spacer(Modifier.height(12.dp))
            MainButton(
                text = stringResource(R.string.confirm),
                onClick = {
                    onEvent(DonationEvent.MakeOrder(data = selectedDrugs, branchId = branchId))
                },
                loading = state.loading
            )

        }
    }
    if (showAddDialog) AddDrugDialog(
        drugs = state.drugs,
        onDismiss = { showAddDialog = false },
        onAdd = { drug, qty ->
            selectedDrugs[drug] = qty
            showAddDialog = false
        }
    )
    if (showOrderIdDialog) OnSuccessDialog(
        pharmacyName = pharmacyName,
        orderId = state.orderId ?: "",
        onDismiss = { showOrderIdDialog = false },
        onDone = { onEvent(DonationEvent.Done) }
    )
}

@Composable
fun AddDrugDialog(
    drugs: List<Drug>,
    onDismiss: () -> Unit,
    onAdd: (Drug, Int) -> Unit,
) {
    var text by remember {
        mutableStateOf("")
    }
    var drug: Drug? by remember {
        mutableStateOf(null)
    }
    var qty by remember {
        mutableStateOf("1")
    }
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AutoCompleteTextField(
                    text = text,
                    onTextChange = { text = it },
                    options = drugs,
                    onOptionSelected = {
                        drug = it
                        text = it.name
                    },
                    optionName = { it.name }
                )
                Spacer(Modifier.height(8.dp))
                MainTextField(
                    value = qty,
                    onValueChange = { qty = it },
                    hint = stringResource(R.string.qty),
                    keyboardType = KeyboardType.Number,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_number),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                )
                Spacer(Modifier.height(16.dp))
                SmallOutlinedButton(
                    text = stringResource(R.string.add_drug)
                ) {
                    drug?.let {
                        onAdd(it, qty.toInt())
                    }
                }
            }
        }
    }
}

@Composable
fun OnSuccessDialog(
    pharmacyName: String,
    orderId: String,
    onDismiss: () -> Unit,
    onDone: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(R.string.dispose_via, pharmacyName),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.align(Alignment.Start)
                )
                Spacer(Modifier.height(12.dp))
                Image(
                    painter = painterResource(R.drawable.checkmark_img),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(0.7f).aspectRatio(1f)
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    stringResource(R.string.your_order_id_is, orderId),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    stringResource(R.string.donation_sucess_description),
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(Modifier.height(12.dp))
                SmallOutlinedButton(stringResource(R.string.done)) { onDone() }
            }
        }
    }
}

@Composable
@Preview
private fun DonationDataScreenPreview() {
    DonationDataScreen(
        state = DonationUiState(),
        branchId = 0,
        pharmacyName = "",
        onEvent = { _ ->  })
}

@Preview
@Composable
private fun OnSuccessDialogPreview() {
    MedEcoTheme {
        OnSuccessDialog(
            "El Ezaby",
            orderId = "33dc3",
            {},
            {}
        )
    }
}

