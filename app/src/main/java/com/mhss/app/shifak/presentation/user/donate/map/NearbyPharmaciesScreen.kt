package com.mhss.app.shifak.presentation.user.donate.map

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.mhss.app.shifak.R
import com.mhss.app.shifak.domain.model.pharmacy.Pharmacy
import com.mhss.app.shifak.domain.model.pharmacy.PharmacyBranch
import com.mhss.app.shifak.presentation.common.Screen
import com.mhss.app.shifak.presentation.common.SmallOutlinedButton
import com.mhss.app.shifak.presentation.user.donate.components.PharmacyMapMarker

@SuppressLint("MissingPermission")
@Composable
fun NearbyPharmaciesScreen(
    state: NearbyPharmaciesUiState,
    onNavigate: (Screen) -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiSettings by remember {
        mutableStateOf(MapUiSettings(myLocationButtonEnabled = true))
    }
    val properties by remember {
        mutableStateOf(MapProperties(
            isMyLocationEnabled = true
        ))
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(state.userLocation, 10f)
    }
    LaunchedEffect(state.userLocation) {
        cameraPositionState.animate(
            update = CameraUpdateFactory.newCameraPosition(
                CameraPosition(state.userLocation, 16f, 0f, 0f)
            ),
            durationMs = 1000
        )
    }
    var dialogPharmacy: Pharmacy? by remember { mutableStateOf(null) }
    var dialogBranch: PharmacyBranch? by remember { mutableStateOf(null) }
    var showDialog by remember { mutableStateOf(false) }
    Scaffold(
        modifier = modifier,
        topBar = {
            Text(
                text = stringResource(R.string.nearby_pharmacies),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp)
                    .windowInsetsPadding(WindowInsets.statusBars),
                textAlign = TextAlign.Center
            )
        },
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                uiSettings = uiSettings,
                properties = properties
            ) {
                state.pharmacies.forEach { pharmacy ->
                    pharmacy.branches?.forEach { branch ->
                        PharmacyMapMarker(
                            pharmacy = pharmacy,
                            state = rememberMarkerState(position = LatLng(branch.lat!!, branch.lng!!)),
                            title = "Titlee",
                            snippet = "Snipp",
                            onClick = { marker ->
                                dialogPharmacy = pharmacy
                                dialogBranch = branch
                                showDialog = true
                                true
                            },
                            onInfoWindowClick = {

                            }
                        )
                    }
                }
            }
        }
    }
    if (showDialog) {
        Dialog(
            onDismissRequest = { showDialog = false }
        ) {
            Card(
                shape = RoundedCornerShape(20.dp)
            ) {
                Column (modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            Modifier
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .size(48.dp)
                        ) {
                            if (dialogPharmacy?.logoBitmap != null) {
                                Image(
                                    bitmap = dialogPharmacy!!.logoBitmap!!,
                                    contentDescription = null,
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier.fillMaxSize()
                                )
                            } else {
                                val randomColor = remember {
                                    Color(
                                        red = (0..200).random(),
                                        green = (0..200).random(),
                                        blue = (0..200).random()
                                    )
                                }
                                Icon(
                                    painter = painterResource(R.drawable.ic_pharmacy),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(8.dp),
                                    tint = randomColor
                                )
                            }
                        }
                        Spacer(Modifier.width(12.dp))
                        Text(
                            text = dialogPharmacy?.name ?: "",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.align(Alignment.Start)) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = stringResource(R.string.location),
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = dialogBranch?.address ?: "",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.align(Alignment.Start)) {
                        Icon(
                            painter = painterResource(R.drawable.ic_phone),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = dialogBranch?.phone ?: "",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Spacer(Modifier.height(16.dp))
                    SmallOutlinedButton(
                        text = stringResource(R.string.select)
                    ) {
                        showDialog = false
                        onNavigate(Screen.DonationDataScreen(branchId = dialogBranch?.id ?: 0, pharmacyName = dialogPharmacy!!.name))
                    }
                }
            }
        }
    }
}