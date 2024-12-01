package com.mhss.app.shifak.presentation.user.donate

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import java.util.UUID
import kotlin.coroutines.CoroutineContext

@Single
class FileManager(
    private val context: Context,
    @Named("ioDispatcher") private val ioDispatcher: CoroutineContext,
) {
    suspend fun uriToByteArray(contentUri: Uri): FileInfo {
        return withContext(ioDispatcher) {
            val bytes = context
                .contentResolver
                .openInputStream(contentUri)
                ?.use { inputStream ->
                    inputStream.readBytes()
                } ?: byteArrayOf()

            val fileName = UUID.randomUUID().toString()
            val mimeType = context.contentResolver.getType(contentUri) ?: ""

            FileInfo(
                name = fileName,
                mimeType = mimeType,
                bytes = bytes
            )
        }
    }
}

@Serializable
class FileInfo(
    val name: String,
    val mimeType: String,
    val bytes: ByteArray
)