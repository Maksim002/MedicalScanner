package com.example.medicalscanner.api

import com.example.medicalscanner.model.ExampleModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface GithubApi {
    @GET("v1/countries?")
    suspend fun news(): ExampleModel
}