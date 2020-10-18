package com.padcx.firebaseeapp.mvp.views

import com.padc.grocery.mvp.views.BaseView

interface LoginView : BaseView {
    fun navigateToHomeScreen()
    fun navigateToRegisterScreen()
}