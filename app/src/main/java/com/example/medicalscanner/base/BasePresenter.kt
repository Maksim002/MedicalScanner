package com.example.medicalscanner.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<V : MvpView> : ViewModel(), MvpPresenter<V>, CoroutineScope{

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Main.immediate

    protected var view: V? = null
        private set

    @CallSuper
    override fun attach(view: V) {
        this.view = view
    }

    @CallSuper
    override fun detach() {
        coroutineContext.cancelChildren()
        view = null
    }
}