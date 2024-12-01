package com.mhss.app.shifak.domain.use_case.user

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import kotlin.coroutines.CoroutineContext

@Single
class UrlToBitmapUseCase(
    private val context: Context,
    @Named("ioDispatcher") val ioDispatcher: CoroutineContext
) {
    suspend operator fun invoke(url: String) = withContext(ioDispatcher) {
        val loader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(url)
            .allowHardware(false)
            .build()
        val result = loader.execute(request)
        if (result is SuccessResult) {
            (result.drawable as BitmapDrawable).bitmap
        } else null
    }
}