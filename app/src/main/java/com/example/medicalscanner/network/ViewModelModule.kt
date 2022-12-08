package com.example.medicalscanner.network

import com.example.medicalscanner.api.GithubApi
import com.example.medicalscanner.main.MainView
import com.example.medicalscanner.main.MainViewPresenter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val vmModule = module {
    viewModel {
        MainViewPresenter(get<Retrofit>().create(GithubApi::class.java))
    } bind MainView.Presenter::class
//    single{ MainViewModel(get<Retrofit>().create(GithubApi::class.java))}
}