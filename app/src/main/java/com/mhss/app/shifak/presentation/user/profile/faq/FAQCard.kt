package com.mhss.app.shifak.presentation.user.profile.faq

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.mhss.app.shifak.domain.model.user.FAQ
import com.mhss.app.shifak.presentation.ui.theme.MedEcoTheme

@Composable
fun FAQCard(
    faq: FAQ,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        onClick = { expanded = !expanded }
    ) {

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(14.dp))
                .background(MaterialTheme.colorScheme.secondary)
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
        ) {
            Box(
                Modifier
                    .width(20.dp)
                    .fillMaxHeight()
                    .background(Color(0xFF125872))

            )
            Text(
                text = faq.question,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
            )
        }

        AnimatedVisibility(expanded) {
            Text(
                text = faq.answer,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .background(Color(0xFFE5F1FF))
                    .padding(16.dp),
                lineHeight = TextUnit(24f, TextUnitType.Sp),
            )
        }


    }
}


@Preview(locale = "ar")
@Composable
private fun FAQCardPreview() {
    MedEcoTheme {
        FAQCard(
            faq = FAQ(
                question = "تعلم كيفية تصوير الدواء بشكل صحيح و كيفية التحقق من سلامة الدواء",
                answer = "استخدم إضاءة جيدة:\nتأكد من أن المكان مضاء جيدًا، ويفضل استخدام إضاءة طبيعية لتجنب الظلال.\n" +
                        "ابتعد عن التشويش:\nضع الدواء على خلفية بسيطة وواضحة مثل طاولة أو سطح أبيض لتجنب تشويش الخلفية.\n" +
                        "ركز على التفاصيل:\nتأكد من أن اسم الدواء، التركيز (الجرعة)، وتاريخ الصلاحية واضحين في الصورة.\n" +
                        "صور من زوايا متعددة:\nقم بتصوير الدواء من عدة زوايا، خاصة إذا كان يحتوي على ملصقات أو معلومات هامة على الجانبين.\n" +
                        "استخدم التركيز التلقائي:\nحافظ على الكاميرا ثابتة وتأكد من استخدام التركيز التلقائي لالتقاط صورة واضحة"
            )
        )
    }
}