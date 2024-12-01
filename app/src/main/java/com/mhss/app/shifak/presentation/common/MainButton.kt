package com.mhss.app.shifak.presentation.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mhss.app.shifak.presentation.ui.theme.PrimaryColor
import com.mhss.app.shifak.presentation.ui.theme.SecondaryColor
import com.mhss.app.shifak.presentation.ui.theme.MedEcoTheme

@Composable
fun MainButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    loading: Boolean = false
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.horizontalGradient(
                    colors = listOf(PrimaryColor, SecondaryColor),
                ),
                shape = ButtonDefaults.shape
            ),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Color.Transparent
        ),
        enabled = !loading
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            AnimatedVisibility(loading) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(48.dp).padding(8.dp)
                )
            }
            Text(
                text = text,
                style = MaterialTheme
                    .typography
                    .headlineSmall
                    .copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
@Preview(widthDp = 320)
fun MainButtonPreview() {
    MedEcoTheme {
        MainButton(
            text = "تسجيل الدخول",
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
            loading = false
        )
    }
}