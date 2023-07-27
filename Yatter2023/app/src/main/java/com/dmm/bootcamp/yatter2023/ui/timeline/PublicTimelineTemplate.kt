package com.dmm.bootcamp.yatter2023.ui.timeline

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dmm.bootcamp.yatter2023.R
import com.dmm.bootcamp.yatter2023.ui.theme.Yatter2023Theme
import com.dmm.bootcamp.yatter2023.ui.timeline.bindingmodel.MediaBindingModel
import com.dmm.bootcamp.yatter2023.ui.timeline.drawer.ProfileBindingModel
import com.dmm.bootcamp.yatter2023.ui.timeline.bindingmodel.StatusBindingModel
import com.dmm.bootcamp.yatter2023.ui.timeline.drawer.DrawerContent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PublicTimelineTemplate(
    statusList: List<StatusBindingModel>,
    profile: ProfileBindingModel,
    isLoading: Boolean,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onClickPost: () -> Unit,
    onClickRow: () -> Unit,
    onClickProfile: () -> Unit,
    onClickLogout: () -> Unit
) {
    val pullRefreshState = rememberPullRefreshState(isRefreshing, onRefresh)
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.public_timeline_topbar)) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                    ) {
                        Icon(Icons.Default.Menu, "Menu")
                    }
                }
            )
        },
        drawerContent = {
            DrawerContent(
                username = profile.username,
                displayName = profile.displayName,
                avatar = profile.avatar,
                followingCount = profile.followingCount,
                followerCount = profile.followerCount,
                onClickProfile = onClickProfile,
                onClickLogout = onClickLogout
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onClickPost,
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.public_timeline_post)
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .pullRefresh(pullRefreshState),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(statusList) { item ->
                    StatusRow(
                        statusBindingModel = item,
                        onClick = onClickRow
                    )
                    Divider(thickness = 1.dp)
                }
            }
            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
            if (isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview
@Composable
private fun PublicTimelineTemplatePreview() {
    Yatter2023Theme {
        Surface {
            PublicTimelineTemplate(
                statusList = listOf(
                    StatusBindingModel(
                        id = "id",
                        displayName = "display name",
                        username = "username",
                        avatar = "https://avatars.githubusercontent.com/u/39693306?v=4",
                        content = "preview content",
                        attachmentMediaList = listOf(
                            MediaBindingModel(
                                id = "id",
                                type = "image",
                                url = "https://avatars.githubusercontent.com/u/39693306?v=4",
                                description = "icon"
                            ),
                            MediaBindingModel(
                                id = "id",
                                type = "image",
                                url = "https://avatars.githubusercontent.com/u/39693306?v=4",
                                description = "icon"
                            ),
                            MediaBindingModel(
                                id = "id",
                                type = "image",
                                url = "https://pbs.twimg.com/profile_banners/972404402425245697/1690337648/1500x500",
                                description = "icon"
                            )
                        )
                    ),
                    StatusBindingModel(
                        id = "id",
                        displayName = "display name",
                        username = "username",
                        avatar = "https://pbs.twimg.com/profile_banners/972404402425245697/1690337648/1500x500",
                        content = "preview content",
                        attachmentMediaList = listOf(
                            MediaBindingModel(
                                id = "id",
                                type = "image",
                                url = "https://avatars.githubusercontent.com/u/39693306?v=4",
                                description = "icon"
                            ),
                            MediaBindingModel(
                                id = "id",
                                type = "image",
                                url = "https://pbs.twimg.com/profile_banners/972404402425245697/1690337648/1500x500",
                                description = "icon"
                            )
                        )
                    ),
                    StatusBindingModel(
                        id = "id",
                        displayName = "display name",
                        username = "username",
                        avatar = null,
                        content = "preview content",
                        attachmentMediaList = listOf()
                    )
                ),
                profile = ProfileBindingModel(
                    username = "reppy4620",
                    displayName = "reppy",
                    avatar = "https://avatars.githubusercontent.com/u/39693306?v=4",
                    followingCount = 50,
                    followerCount = 20,
                ),
                isLoading = true,
                isRefreshing = false,
                onRefresh = {},
                onClickPost = {},
                onClickProfile = {},
                onClickRow = {},
                onClickLogout = {},
            )
        }
    }
}