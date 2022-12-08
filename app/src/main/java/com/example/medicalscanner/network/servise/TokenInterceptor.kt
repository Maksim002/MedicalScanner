package com.example.medicalscanner.network.servise

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val tock = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkZXZpY2VfaWQiOiJVTktOT1dOIiwidXNlcl9uYW1lIjoiOTk2NzczOTc5MDI2Iiwic2NvcGUiOlsiYWxsIl0sImV4cCI6MTY3MDU3MDM1NSwiY3VzdG9tZXJfaWQiOjIyNTQ3MzYsImlzc3VlZF9hdCI6MTY3MDQ4Mzk1NTEzMiwiYXV0aG9yaXRpZXMiOlsiYWNjb3VudF9sb2cucmVhZCIsInBheW1lbnQucmVhZCIsImF1dGhvcml6YXRpb24ubW9iaWxlMiIsInBheW1lbnQuY2FuY2VsIiwiYXV0aG9yaXphdGlvbi5tb2JpbGUiLCJwYXltZW50LmNyZWF0ZSJdLCJqdGkiOiJhZDkxNDk4MC02NWQwLTQyYjctODE5MC1kODkyMGExMjFjYTIiLCJjbGllbnRfaWQiOiJtb2JpbGVJZCJ9.Loyy2Wu0rMMUh7rYdlb6-cD4tlWr6aQJKHz8EoJaeH4"
        val newRequest = request.newBuilder().apply {
//            header("Authorization", "Bearer $tock")
            addHeader("Accept", "application/json")
        }
        return chain.proceed(newRequest.build())
    }
}