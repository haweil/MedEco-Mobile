package com.mhss.app.shifak.presentation.user.profile.faq

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mhss.app.shifak.R
import com.mhss.app.shifak.domain.model.user.FAQ
import com.mhss.app.shifak.presentation.common.MainTopAppBar

@Composable
fun FAQScreen(
    onNavigateUp : () -> Unit,
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MainTopAppBar(
            title = stringResource(id = R.string.faq),
            onNavigateUp = onNavigateUp,
            modifier = Modifier.navigationBarsPadding()
        )

        Spacer(Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(faqList) { faq ->
                FAQCard(faq)
            }
        }


    }
}

val faqList = listOf(
    FAQ(
        question = "تعلم كيفية تصوير الدواء بشكل صحيح و كيفية التحقق من سلامة الدواء",
        answer = "استخدم إضاءة جيدة:\nتأكد من أن المكان مضاء جيدًا، ويفضل استخدام إضاءة طبيعية لتجنب الظلال. ابتعد عن التشويش:\nضع الدواء على خلفية بسيطة وواضحة مثل طاولة أو سطح أبيض لتجنب تشويش الخلفية. ركز على التفاصيل:\nتأكد من أن اسم الدواء، التركيز (الجرعة)، وتاريخ الصلاحية واضحين في الصورة. صور من زوايا متعددة:\nقم بتصوير الدواء من عدة زوايا، خاصة إذا كان يحتوي على ملصقات أو معلومات هامة على الجانبين. استخدم التركيز التلقائي:\nحافظ على الكاميرا ثابتة وتأكد من استخدام التركيز التلقائي لالتقاط صورة واضحة"
    ),
    FAQ(
        question = "تعلم كيف تتم عملية تسليم الدواء",
        answer = ""
    ),
    FAQ(
        question = "تعلم كيفية تصوير الدواء بشكل صحيح و كيفية التحقق من سلامة الدواء",
        answer = ""
    ),
    FAQ(
        question = "خطورة تناول بعض الأطعمة مع الأدوية",
        answer = ""
    )
)

