package com.dmm.bootcamp.yatter2023.ui.timeline

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import com.dmm.bootcamp.yatter2023.ui.timeline.drawer.DrawerViewModel

@Composable
fun PublicTimelinePage(
    publicTimelineViewModel: PublicTimelineViewModel,
    drawerViewModel: DrawerViewModel
) {
    val timelineUiState by publicTimelineViewModel.uiState.collectAsStateWithLifecycle()
    val drawerUiState by drawerViewModel.uiState.collectAsStateWithLifecycle()

    PublicTimelineTemplate(
        statusList = timelineUiState.statusList,
        profile = drawerUiState.bindingModel,
        isLoading = timelineUiState.isLoading,
        isRefreshing = timelineUiState.isRefreshing,
        onRefresh = publicTimelineViewModel::onRefresh,
        onClickPost = publicTimelineViewModel::onClickPost,
        onClickProfile = drawerViewModel::onClickProfile
    )
}