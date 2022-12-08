package com.example.medicalscanner.network.servise

import com.google.gson.annotations.SerializedName

data class ServerErrorWrapper(
    @SerializedName("status")
    val status: ServerStatus,

    @SerializedName("error")
    val data: ServerErrorData?,

    @SerializedName("message")
    val message: String?,

    @SerializedName("error_description")
    val errorDescription: String?
)

data class ServerErrorData(
    @SerializedName("field")
    val field: String,

    @SerializedName("error")
    val code: ErrorCode,

    @SerializedName("error_description")
    val message: String,
)

data class ServerErrorA3F(
    @SerializedName("error")
    val error: String,

    @SerializedName("error_description")
    val errorDescription: String,

    @SerializedName("a3f_token")
    val token: String,
)