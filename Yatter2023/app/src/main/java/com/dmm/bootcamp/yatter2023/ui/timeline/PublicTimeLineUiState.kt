package com.dmm.bootcamp.yatter2023.ui.timeline

import com.dmm.bootcamp.yatter2023.ui.timeline.bindingmodel.StatusBindingModel

data class PublicTimeLineUiState(
    val statusList: List<StatusBindingModel>,
    val isLoading: Boolean,
    val isRefreshing: Boolean
) {
    companion object {
        fun empty(): PublicTimeLineUiState = PublicTimeLineUiState(
            statusList = emptyList(),
            isLoading = false,
            isRefreshing = false
        )
    }
}