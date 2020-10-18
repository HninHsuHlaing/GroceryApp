package com.padc.grocery.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.padcx.firebaseeapp.data.models.AuthenticationModel
import com.padcx.firebaseeapp.data.models.AuthenticationModelImpl
import com.padc.grocery.mvp.presenters.AbstractBasePresenter
import com.padcx.firebaseeapp.mvp.presenters.RegisterPresenter
import com.padcx.firebaseeapp.mvp.views.RegisterView

class RegisterPresenterImpl : RegisterPresenter, AbstractBasePresenter<RegisterView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    override fun onTapRegister(email: String, password: String, userName: String) {
        mAuthenticationModel.register(email, password, userName, onSuccess = {
            mView.navigateToToLoginScreen()
        }, onFailure = {
            mView.showError(it)
        })
    }

    override fun onUiReady(owner: LifecycleOwner) {}
}