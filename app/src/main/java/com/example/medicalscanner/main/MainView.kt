package com.example.medicalscanner.main

import com.example.medicalscanner.base.MvpPresenter
import com.example.medicalscanner.base.MvpView
import com.example.medicalscanner.model.Data

interface MainView{
    interface View : MvpView {
        fun textAr(data : Map<String, Data>)
    }

    interface Presenter : MvpPresenter<View> {}
}