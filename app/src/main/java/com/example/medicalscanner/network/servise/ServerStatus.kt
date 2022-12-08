package com.example.medicalscanner.network.servise

enum class ServerStatus(val stringValue: String) {
    SUCCESS("SUCCESS"),
    FAIL("FAIL"),
    ERROR("ERROR");

    companion object {
        fun parse(value: String?): ServerStatus = when (value) {
            SUCCESS.stringValue -> SUCCESS
            FAIL.stringValue -> FAIL
            ERROR.stringValue -> ERROR
            else -> SUCCESS
        }
    }
}