package com.example.medicalscanner.network.servise

import com.example.medicalscanner.R
import com.google.gson.annotations.SerializedName

private const val DEFAULT_MESSAGE_RES = R.string.general_error_message

enum class ErrorCode(val messageRes: Int = DEFAULT_MESSAGE_RES) {
    @SerializedName("1")
    WRONG_NUMBER_FORMAT(R.string.error_wrong_number_format),

    @SerializedName("2")
    USER_NOT_FOUND(R.string.error_user_not_found),

    @SerializedName("3")
    USER_BLOCKED(R.string.error_user_blocked),

    @SerializedName("15")
    DEALER_NOT_FOUND(R.string.error_dealer_not_found),

    @SerializedName("17")
    SUB_DEALER_ACCOUNT_ERROR(R.string.error_sub_dealer_account_error),

    @SerializedName("unauthorized")
    UNAUTHORIZED,

    @SerializedName("500")
    SERVER_ERROR;

    val hasSpecifiedMessage = messageRes != DEFAULT_MESSAGE_RES
}