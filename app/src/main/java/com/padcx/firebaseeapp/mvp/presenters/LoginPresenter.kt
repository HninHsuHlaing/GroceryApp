package com.padcx.firebaseeapp.mvp.presenters


import com.padc.grocery.mvp.presenters.BasePresenter
import com.padcx.firebaseeapp.mvp.views.LoginView

interface LoginPresenter : BasePresenter<LoginView> {
    fun onTapLogin(email: String, password: String)
    fun onTapRegister()
}