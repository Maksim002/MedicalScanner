package com.example.medicalscanner.network.servise

import android.content.Context
import java.io.IOException

open class ServerException(
    val code: Int,
    val errorCode: ErrorCode?,
    private val errorMessage: String?
) : IOException("statusCode: $errorCode, errorMessage: $errorMessage") {

    fun getErrorMessage(context: Context) =
        if (!errorMessage.isNullOrEmpty()) errorMessage
        else context.getString((errorCode ?: ErrorCode.SERVER_ERROR).messageRes)
}