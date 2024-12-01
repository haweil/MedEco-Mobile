package com.mhss.app.shifak.domain.model.user

data class User(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val fullName: String,
    val phone: String,
    val email: String,
    val nationalId: String,
    val birthdate: String,
    val age: Int,
    val lat: Double,
    val lng: Double,
    val emailVerified: Boolean,
    val isBlocked: Boolean,
    val role: Role,
    val createdAt: Long,
    val updatedAt: Long,
    val avatarUrl: String? = null
)

