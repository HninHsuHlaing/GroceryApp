package com.padcx.firebaseeapp.mvp.presenters


import com.padc.grocery.mvp.presenters.BasePresenter
import com.padcx.firebaseeapp.mvp.views.RegisterView

interface RegisterPresenter : BasePresenter<RegisterView> {
    fun onTapRegister(email: String, password: String, userName: String)
}