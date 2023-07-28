package com.dmm.bootcamp.yatter2023.ui.edit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Surface
import com.dmm.bootcamp.yatter2023.ui.theme.Yatter2023Theme
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditActivity: AppCompatActivity() {
    companion object {
        fun newIntent(context: Context): Intent = Intent(
            context,
            EditActivity::class.java
        )
    }

    val viewModel: EditViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Yatter2023Theme {
                Surface {
                    EditPage(viewModel = viewModel)
                }
            }
        }

        viewModel.onCreate()

        viewModel.goBack.observe(this) {
            finish()
        }
    }
}