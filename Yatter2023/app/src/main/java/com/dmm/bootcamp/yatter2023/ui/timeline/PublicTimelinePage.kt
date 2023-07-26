package com.dmm.bootcamp.yatter2023.ui.timeline

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue

@Composable
fun PublicTimelinePage(viewModel: PublicTimelineViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PublicTimelineTemplate(
        statusList = uiState.statusList,
        isLoading = uiState.isLoading,
        isRefreshing = uiState.isRefreshing,
        onRefresh = viewModel::onRefresh,
        onClickPost = viewModel::onClickPost
    )
}