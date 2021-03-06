package com.padcx.firebaseeapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.padc.grocery.activities.BaseActivity
import com.padcx.firebaseeapp.mvp.presenters.RegisterPresenter
import com.padc.grocery.mvp.presenters.impls.RegisterPresenterImpl
import com.padcx.firebaseeapp.mvp.views.RegisterView
import com.padcx.firebaseeapp.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(), RegisterView {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    private lateinit var mPresenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setUpPresenter()
        setUpActionListeners()
    }

    private fun setUpActionListeners() {
        btnRegister.setOnClickListener {
            mPresenter.onTapRegister(
                etEmail.text.toString(),
                etPassword.text.toString(),
                etUserName.text.toString()
            )
        }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<RegisterPresenterImpl, RegisterView>()
    }

    override fun navigateToToLoginScreen() {
        startActivity(LoginActivity.newIntent(this))
    }
}