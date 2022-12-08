package com.example.medicalscanner.network.servise

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class ServerErrorInterceptor(
    private val gson: Gson,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val isFile = when(response.header("content-type")){
            "application/octet-stream;charset=UTF-8" -> true
            "image/png;charset=UTF-8" -> true
            else -> false
        }
        return if (isFile) response else handleResponse(response)
    }

    @Throws(Exception::class)
    private fun handleResponse(response: Response): Response {
        try {
            var bodyString = response.body!!.string()
            val jsonObject = JSONObject(bodyString)
            val message = jsonObject.opt("message") as? String
            var status = jsonObject.optString("status")
            val dataObject = jsonObject.optJSONObject("data")
            bodyString = if (message.isNullOrEmpty() || dataObject == null) {
                bodyString
            } else {
                val data = dataObject.apply { putOpt("message", message) }
                jsonObject.put("data", data)
                jsonObject.toString()
            }
            val finalMessage = message ?: dataObject?.optString("message") ?: jsonObject.optString("data")
            if (status.isNullOrEmpty()) {
                bodyString = mapToGeneralModel(bodyString, response.isSuccessful, finalMessage).toString()
                status = JSONObject(bodyString).optString("status")
            }
            return when (ServerStatus.parse(status)) {
                ServerStatus.SUCCESS -> buildResponseBody(response, bodyString)
                ServerStatus.FAIL -> throw extractFailException(finalMessage, response.code)
                ServerStatus.ERROR -> throw extractException(bodyString, response.code)
            }
        } catch (e: JSONException) {
            throw extractFailException("Ошибка соединения с сервером", 500)
        }
    }

    // todo: workaround. isn't it expensive mapping?
    private fun buildResponseBody(response: Response, bodyString: String) =
        response
            .newBuilder()
            .body(bodyString.toResponseBody(null))
            .build()

    private fun mapToGeneralModel(
        bodyString: String,
        isSuccessful: Boolean,
        message: String
    ): JSONObject {
        val commonObject = JSONObject()
        if (isSuccessful) {
            commonObject.put("status", "SUCCESS")
            commonObject.put("data", JSONObject(bodyString).apply { putOpt("message", message) })
        } else {
            commonObject.put("status", "ERROR")
            commonObject.put("error", JSONObject(bodyString))
        }
        return commonObject
    }

    private fun extractFailException(finalMessage: String, code: Int): Throwable = try {
        ServerException(
            code = code,
            errorCode = ErrorCode.SERVER_ERROR,
            errorMessage = finalMessage
        )
    } catch (e: Throwable) {
        IOException("Error reading response error body", e)
    }

    private fun extractException(bodyString: String, code: Int): Throwable {
        try {
            when {
                code == 403 && (
                        gson.fromJson(
                            JSONObject(bodyString).optJSONObject("error")?.toString(),
                            ServerErrorA3F::class.java
                        ) != null
                        ) -> {
                    val serverError = gson.fromJson(
                        JSONObject(bodyString).optJSONObject("error")!!.toString(),
                        ServerErrorA3F::class.java
                    )
                    return A3FException(
                        error = serverError.error,
                        errorDescription = serverError.errorDescription,
                        token = serverError.token
                    )
                }

                else -> {
                    val serverError = gson.fromJson(bodyString, ServerErrorWrapper::class.java)
                    return ServerException(
                        code = code,
                        errorCode = serverError?.data?.code,
                        errorMessage = serverError?.message ?: serverError?.data?.message
                    )
                }
            }
        } catch (e: Throwable) {
            return IOException("Error reading response error body", e)
        }
    }
}