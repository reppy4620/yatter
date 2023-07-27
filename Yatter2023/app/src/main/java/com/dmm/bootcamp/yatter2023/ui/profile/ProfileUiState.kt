package com.dmm.bootcamp.yatter2023.ui.profile

data class ProfileUiState(
    val bindingModel: ProfileBindingModel,
    val isLoading: Boolean,
) {
    companion object {
        fun empty() = ProfileUiState(
            bindingModel = ProfileBindingModel(
                username = "",
                displayName = "",
                note = "",
                avatar = null,
                header = null,
                followingCount = 0,
                followerCount = 0
            ),
            isLoading = false
        )
    }
}