package com.mhss.app.shifak.domain.model.assistant


sealed interface NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>
    data object InternetError : UserError
    data class OtherError(val message: String? = null): Failure

    sealed interface Failure: NetworkResult<Nothing>
    sealed interface UserError: Failure
}
