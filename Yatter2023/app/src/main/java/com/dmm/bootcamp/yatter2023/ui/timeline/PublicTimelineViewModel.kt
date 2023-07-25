package com.dmm.bootcamp.yatter2023.ui.timeline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmm.bootcamp.yatter2023.domain.repository.StatusRepository
import com.dmm.bootcamp.yatter2023.ui.timeline.bindingmodel.converter.StatusConverter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PublicTimelineViewModel(
    private val statusRepository: StatusRepository,
): ViewModel() {
    private val _uiState: MutableStateFlow<PublicTimeLineUiState> =
        MutableStateFlow(PublicTimeLineUiState.empty())
    val uiState: StateFlow<PublicTimeLineUiState> = _uiState

    private suspend fun fetchPublicTimeline() {
        val statusList = statusRepository.findAllPublic()
        _uiState.update {
            it.copy(
                statusList = StatusConverter.convertToBindingModel(statusList)
            )
        }
    }

    fun onResume() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            fetchPublicTimeline()
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun onRefresh() {
        viewModelScope.launch {
            _uiState.update { it.copy(isRefreshing = true) }
            fetchPublicTimeline()
            _uiState.update { it.copy(isRefreshing = false) }
        }
    }
}