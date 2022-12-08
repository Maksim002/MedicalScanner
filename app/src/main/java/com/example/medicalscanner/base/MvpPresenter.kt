package com.example.medicalscanner.base

interface MvpPresenter<V : MvpView> {

    fun attach(view: V)

    fun detach()
}