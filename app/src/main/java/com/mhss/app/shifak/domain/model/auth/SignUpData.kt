package com.mhss.app.shifak.domain.model.auth

import android.net.Uri
import com.mhss.app.shifak.util.Gender
import com.mhss.app.shifak.util.UserType

data class SignUpData(
    val fullName: String,
    val phone: String,
    val nationalId: String,
    val email: String,
    val password: String,
    val passwordConf: String,
    val birthDate: Long,
    val gender: Gender,
    val type: UserType,
    // below specific for UserType.PHARMACY
    val pharmacyName: String? = null,
    val pharmacyHotline: String? = null,
    val pharmacyLogo: Uri? = null,
)
