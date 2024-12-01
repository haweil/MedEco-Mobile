package com.mhss.app.shifak.presentation.user.donate.approve

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mhss.app.shifak.R
import com.mhss.app.shifak.data.remote.pharmacy.model.DonationDrug
import com.mhss.app.shifak.data.remote.pharmacy.model.DonationDrugItem
import com.mhss.app.shifak.data.remote.pharmacy.model.GetDonationData
import com.mhss.app.shifak.data.remote.pharmacy.model.GetDonationDto
import com.mhss.app.shifak.presentation.common.EventStatusDialog
import com.mhss.app.shifak.presentation.common.MainButton
import com.mhss.app.shifak.presentation.common.MainTextField
import com.mhss.app.shifak.presentation.common.MainTopAppBar
import com.mhss.app.shifak.presentation.ui.theme.MedEcoTheme
import com.mhss.app.shifak.presentation.user.donate.components.DrugCard

@Composable
fun ApproveDonationScreen(
    state: ApproveDonationUiState,
    onEvent: (ApproveDonationEvent) -> Unit,
) {
    var orderId by remember {
        mutableStateOf("")
    }
    var showOrder by remember {
        mutableStateOf(true)
    }
    var showSuccessDialog by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    LaunchedEffect(state.success) {
        if (state.success) {
            showOrder = false
            showSuccessDialog = true
        }
    }
    LaunchedEffect(state.error) {
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
            MainTextField(
                value = orderId,
                onValueChange = { orderId = it },
                hint = stringResource(R.string.order_id),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))
            MainButton(
                text = stringResource(R.string.search),
                loading = state.loading,
                onClick = {
                    showOrder = true
                    onEvent(ApproveDonationEvent.GetOrder(orderId))
                }
            )
            Spacer(Modifier.height(24.dp))
            if (showOrder) {
                AnimatedContent(state.order, label = "order") { order ->
                    order?.let {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            for (item in order.items) {
                                DrugCard(item.drug.name, item.quantity)
                                Spacer(Modifier.height(8.dp))
                            }
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
                                    text = String.format("%,d", order.points),
                                    style = MaterialTheme.typography.titleLarge.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                )
                            }
                            Spacer(Modifier.height(12.dp))
                            MainButton(
                                text = stringResource(R.string.confirm),
                                loading = state.approveLoading,
                                onClick = {
                                    onEvent(ApproveDonationEvent.Approve(orderId))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
    if (showSuccessDialog) EventStatusDialog(
        text = stringResource(R.string.points_sent_to_user),
        imagePainter = painterResource(R.drawable.home_points_image),
        onDismiss = { showSuccessDialog = false }
    )
}

@Preview
@Composable
private fun ApproveDonationScreenPreview() {
    MedEcoTheme {
        ApproveDonationScreen(
            state = ApproveDonationUiState(
                order =
                GetDonationData(
                    id = 1,
                    items = listOf(
                        DonationDrugItem(
                            drug = DonationDrug(
                                id = 0,
                                name = "Panadol Extra"
                            ),
                            quantity = 7
                        ),
                        DonationDrugItem(
                            drug = DonationDrug(
                                id = 1,
                                name = "Panadol cold + flu"
                            ),
                            quantity = 4
                        ),
                        DonationDrugItem(
                            drug = DonationDrug(
                                id = 3,
                                name = "Demo drug"
                            ),
                            quantity = 7
                        )
                    ),
                    points = 4263
                ),
            ),
            {}
        )
    }
}