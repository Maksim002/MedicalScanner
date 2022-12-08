package com.example.medicalscanner.main

import com.example.medicalscanner.api.GithubApi
import com.example.medicalscanner.base.BasePresenter
import kotlinx.coroutines.launch

class MainViewPresenter(private val githubApi: GithubApi):BasePresenter<MainView.View>(),
    MainView.Presenter  {

    override fun attach(view: MainView.View) {
        super.attach(view)
        test()
    }

    private fun test(){
        launch {
            try {
                val s = githubApi.news()
                view?.textAr(s.data.orEmpty())
            }catch (e: Exception){
                view?.showErrorMessage(e)
            }
        }
    }
}
