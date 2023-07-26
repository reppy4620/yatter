package com.dmm.bootcamp.yatter2023.ui.register_account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Surface
import com.dmm.bootcamp.yatter2023.ui.login.LoginActivity
import com.dmm.bootcamp.yatter2023.ui.theme.Yatter2023Theme
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterAccountActivity: AppCompatActivity() {
    companion object {
        fun newIntent(context: Context): Intent = Intent(
            context,
            RegisterAccountActivity::class.java
        )
    }

    private val viewModel: RegisterAccountViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Yatter2023Theme {
                Surface {
                    RegisterAccountPage(viewModel = viewModel)
                }
            }
        }

        viewModel.navigateToLogin.observe(this) {
            startActivity(LoginActivity.newIntent(this))
            finish()
        }
        viewModel.goBack.observe(this) {
            finish()
        }
    }
}