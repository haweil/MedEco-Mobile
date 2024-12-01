package com.mhss.app.shifak.presentation.user.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mhss.app.shifak.R
import com.mhss.app.shifak.presentation.common.SmallOutlinedButton
import com.mhss.app.shifak.presentation.ui.theme.MedEcoTheme

@Composable
fun HomeDonateCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(18.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1.2f)
            ) {
                Text(
                    text = stringResource(R.string.donate_home_card),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Spacer(Modifier.height(12.dp))
                SmallOutlinedButton(
                    text = stringResource(R.string.donate_now),
                    onClick = onClick
                )
            }
            Image(
                painter = painterResource(R.drawable.donate_home_card_img),
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        end = 12.dp,
                        start = 24.dp
                    )
                    .aspectRatio(1f)
            )
        }
    }
}

@Preview
@Composable
fun HomeDonateCardPreview() {
    MedEcoTheme {
        HomeDonateCard(
            modifier = Modifier.padding(12.dp),
            onClick = {}
        )
    }
}
