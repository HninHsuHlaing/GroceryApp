package com.padcx.firebaseeapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.padc.grocery.activities.BaseActivity
import com.padc.grocery.activities.MainActivity
import com.padcx.firebaseeapp.mvp.presenters.LoginPresenter
import com.padc.grocery.mvp.presenters.impls.LoginPresenterImpl
import com.padcx.firebaseeapp.mvp.views.LoginView
import com.padcx.firebaseeapp.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginView {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    private lateinit var mPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(findViewById(R.id.toolbar))

        setUpPresenter()
        setUpActionListeners()
        mPresenter.onUiReady(this)
    }

    private fun setUpActionListeners() {
        btnLogin.setOnClickListener {
            mPresenter.onTapLogin(etEmail.text.toString(), etPassword.text.toString())
        }

        btnRegister.setOnClickListener {
            mPresenter.onTapRegister()
        }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<LoginPresenterImpl, LoginView>()
    }

    override fun navigateToHomeScreen() {
        startActivity(MainActivity.newIntent(this))
    }

    override fun navigateToRegisterScreen() {
        startActivity(RegisterActivity.newIntent(this))
    }
}