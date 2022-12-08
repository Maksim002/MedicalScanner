package com.example.medicalscanner.network

import com.example.medicalscanner.network.servise.InjectionModule
import com.example.medicalscanner.network.servise.ServerErrorInterceptor
import com.example.medicalscanner.network.servise.TokenInterceptor
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule : InjectionModule {

    override fun create() = module {
        single { Cache(androidApplication().cacheDir, 10L * 1024 * 1024) }
        single { GsonBuilder().create() }
        single { getRetrofitBuilder() }
    }

    private fun Scope.getRetrofitBuilder(): Retrofit {
        val client = createOkHttpClient()
            .apply { addInterceptor(createLoggingInterceptor()) }
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.first.org/data/")
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(client)
            .build()
    }

    private fun Scope.createOkHttpClient(): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(ServerErrorInterceptor(get()))
            .addInterceptor(TokenInterceptor())


    private fun createLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}