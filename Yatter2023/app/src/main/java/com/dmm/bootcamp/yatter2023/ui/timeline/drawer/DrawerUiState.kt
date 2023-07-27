package com.dmm.bootcamp.yatter2023.ui.timeline.drawer

data class DrawerUiState(
    val bindingModel: ProfileBindingModel,
    val isLoading: Boolean
) {
    companion object {
        fun empty() = DrawerUiState(
            bindingModel = ProfileBindingModel(
                username = "",
                displayName = "",
                avatar = null,
                followingCount = 0,
                followerCount = 0,
            ),
            isLoading = false
        )
    }
}