package com.example.medicalscanner.main

import android.os.Bundle
import android.widget.Toast
import com.example.medicalscanner.R
import com.example.medicalscanner.base.BaseMvpActivity
import com.example.medicalscanner.model.Data
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : BaseMvpActivity<MainView.View, MainView.Presenter>(), MainView.View {

    override val presenter: MainView.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun textAr(data: Map<String, Data>){
        textEEE.text = data.size.toString()
    }

    override fun showErrorMessage(e: Throwable?, dismissCallback: (() -> Unit)?) {}

    override fun showErrorMessage(messageRes: Int) {
        Toast.makeText(this, messageRes.toString(), Toast.LENGTH_SHORT).show()
    }
}