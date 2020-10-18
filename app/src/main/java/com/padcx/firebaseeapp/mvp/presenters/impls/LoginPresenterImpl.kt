package com.padc.grocery.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.padc.grocery.data.models.GroceryModel
import com.padc.grocery.data.models.GroceryModelImpl
import com.padcx.firebaseeapp.data.models.AuthenticationModel
import com.padcx.firebaseeapp.data.models.AuthenticationModelImpl
import com.padc.grocery.mvp.presenters.AbstractBasePresenter
import com.padcx.firebaseeapp.mvp.presenters.LoginPresenter
import com.padcx.firebaseeapp.mvp.views.LoginView

class LoginPresenterImpl : LoginPresenter, AbstractBasePresenter<LoginView>() {

    private val mAuthenticatioModel: AuthenticationModel = AuthenticationModelImpl

    private val mGroceryModel:GroceryModel = GroceryModelImpl

    override fun onUiReady(owner: LifecycleOwner) {
       mGroceryModel.setRemoteConfigWithDefaultValues()
        mGroceryModel.fetchRemoteConfig()
        mGroceryModel.setRemoteConfigValueForRecyclerView()
        mGroceryModel.fetchRemoteConfigForRecyclerView()
    }

    override fun onTapLogin(email: String, password: String) {
        mAuthenticatioModel.login(email, password, onSuccess = {
            mView.navigateToHomeScreen()
        }, onFailure = {
          //  mView.showError(it)
        mView.showError(it)
        })
    }

    override fun onTapRegister() {
        mView.navigateToRegisterScreen()
    }
}