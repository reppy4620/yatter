package com.dmm.bootcamp.yatter2023.ui.timeline

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmm.bootcamp.yatter2023.domain.repository.StatusRepository
import com.dmm.bootcamp.yatter2023.ui.timeline.bindingmodel.converter.StatusConverter
import com.dmm.bootcamp.yatter2023.util.SingleLiveEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PublicTimelineViewModel(
    private val statusRepository: StatusRepository,
): ViewModel() {
    private val _uiState: MutableStateFlow<PublicTimelineUiState> =
        MutableStateFlow(PublicTimelineUiState.empty())
    val uiState: StateFlow<PublicTimelineUiState> = _uiState

    private val _navigateToPost: SingleLiveEvent<Unit> = SingleLiveEvent()
    val navigateToPost: LiveData<Unit> = _navigateToPost

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

    fun onClickPost() {
        _navigateToPost.value = Unit
    }
}