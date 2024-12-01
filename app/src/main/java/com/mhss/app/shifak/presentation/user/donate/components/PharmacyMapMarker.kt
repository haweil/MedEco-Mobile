package com.mhss.app.shifak.presentation.user.donate.components

import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.SubcomposeAsyncImage
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberMarkerState
import com.mhss.app.shifak.R
import com.mhss.app.shifak.domain.model.pharmacy.Pharmacy
import com.mhss.app.shifak.domain.model.pharmacy.PharmacyBranch
import com.mhss.app.shifak.presentation.ui.theme.MedEcoTheme

@Composable
fun PharmacyMapMarker(
    pharmacy: Pharmacy,
    state: MarkerState = rememberMarkerState(),
    snippet: String? = null,
    title: String? = null,
    onClick: (Marker) -> Boolean = { false },
    onInfoWindowClick: (Marker) -> Unit = {},
) {
    MarkerComposable(
        state = state,
        snippet = snippet,
        title = title,
        onClick = onClick,
        onInfoWindowClick = onInfoWindowClick,
    ) {
        Box(
            Modifier
                .border(BorderStroke(3.dp, Color.White), CircleShape)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .size(42.dp)
        ) {
            if (pharmacy.logoBitmap != null) {
                Image(
                    bitmap = pharmacy.logoBitmap!!,
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
                    modifier = Modifier.fillMaxSize().padding(8.dp),
                    tint = randomColor
                )
            }
        }
    }
}