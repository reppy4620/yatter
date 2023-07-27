package com.dmm.bootcamp.yatter2023.ui.timeline

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Surface
import com.dmm.bootcamp.yatter2023.ui.login.LoginActivity
import com.dmm.bootcamp.yatter2023.ui.post.PostActivity
import com.dmm.bootcamp.yatter2023.ui.profile.ProfileActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.dmm.bootcamp.yatter2023.ui.theme.Yatter2023Theme
import com.dmm.bootcamp.yatter2023.ui.timeline.drawer.DrawerViewModel

class PublicTimelineActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context): Intent = Intent(
            context,
            PublicTimelineActivity::class.java
        )
    }

    private val publicTimelineViewModel: PublicTimelineViewModel by viewModel()
    private val drawerViewModel: DrawerViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Yatter2023Theme {
                Surface {
                    PublicTimelinePage(
                        publicTimelineViewModel = publicTimelineViewModel,
                        drawerViewModel = drawerViewModel
                    )
                }
            }
        }

        publicTimelineViewModel.navigateToPost.observe(this) {
            startActivity(PostActivity.newIntent(this))
        }

        drawerViewModel.navigateToProfile.observe(this) {
            startActivity(ProfileActivity.newIntent(this))
        }

        drawerViewModel.navigateToLogin.observe(this) {
            startActivity(LoginActivity.newIntent(this))
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        publicTimelineViewModel.onResume()
        drawerViewModel.onResume()
    }
}