package com.example.medicalscanner.network.servise

import okio.IOException

data class A3FException(
    val error: String,
    val errorDescription: String,
    val token: String
) : IOException("error: $error, errorDescription: $errorDescription, token: $token")