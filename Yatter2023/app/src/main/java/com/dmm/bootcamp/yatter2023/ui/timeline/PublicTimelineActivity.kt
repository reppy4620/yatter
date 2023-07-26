package com.dmm.bootcamp.yatter2023.ui.timeline

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Surface
import com.dmm.bootcamp.yatter2023.ui.post.PostActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.dmm.bootcamp.yatter2023.ui.theme.Yatter2023Theme

class PublicTimelineActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context): Intent = Intent(
            context,
            PublicTimelineActivity::class.java
        )
    }

    private val viewModel: PublicTimelineViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Yatter2023Theme {
                Surface {
                    PublicTimelinePage(viewModel = viewModel)
                }
            }
        }

        viewModel.navigateToPost.observe(this) {
            startActivity(PostActivity.newIntent(this))
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }
}