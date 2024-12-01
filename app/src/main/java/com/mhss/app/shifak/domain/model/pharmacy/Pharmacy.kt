package com.mhss.app.shifak.domain.model.pharmacy

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Pharmacy(
    @SerialName("hotline")
    val hotline: String,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("logo")
    val logo: String? = null,
    @SerialName("branches")
    val branches: List<PharmacyBranch>? = null,
) {
    @Transient
    var logoBitmap: ImageBitmap? = null
}