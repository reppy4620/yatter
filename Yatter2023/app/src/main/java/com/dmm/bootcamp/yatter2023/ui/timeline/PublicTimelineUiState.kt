package com.dmm.bootcamp.yatter2023.ui.timeline

import com.dmm.bootcamp.yatter2023.ui.timeline.drawer.ProfileBindingModel
import com.dmm.bootcamp.yatter2023.ui.timeline.bindingmodel.StatusBindingModel

data class PublicTimelineUiState(
    val statusList: List<StatusBindingModel>,
    val profileBindingModel: ProfileBindingModel,
    val isLoading: Boolean,
    val isRefreshing: Boolean
) {
    companion object {
        fun empty(): PublicTimelineUiState = PublicTimelineUiState(
            statusList = emptyList(),
            profileBindingModel = ProfileBindingModel(
                username = "",
                displayName = "",
                avatar = null,
                followingCount = 0,
                followerCount = 0,
            ),
            isLoading = false,
            isRefreshing = false
        )
    }
}