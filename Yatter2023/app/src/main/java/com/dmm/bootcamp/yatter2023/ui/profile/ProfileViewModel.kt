package com.dmm.bootcamp.yatter2023.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmm.bootcamp.yatter2023.domain.repository.StatusRepository
import com.dmm.bootcamp.yatter2023.domain.service.GetMeService
import com.dmm.bootcamp.yatter2023.ui.profile.bindingmodel.converter.ProfileConverter
import com.dmm.bootcamp.yatter2023.ui.timeline.bindingmodel.converter.StatusConverter
import com.dmm.bootcamp.yatter2023.util.SingleLiveEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getMeService: GetMeService,
    private val statusRepository: StatusRepository,
): ViewModel() {
    private val _uiState: MutableStateFlow<ProfileUiState> = MutableStateFlow(ProfileUiState.empty())
    val uiState: StateFlow<ProfileUiState> = _uiState

    private val _navigateToEdit: SingleLiveEvent<Unit> = SingleLiveEvent()
    val navigateToEdit: LiveData<Unit> = _navigateToEdit

    private val _goBack: SingleLiveEvent<Unit> = SingleLiveEvent()
    val goBack: LiveData<Unit> = _goBack

    private suspend fun fetchHome() {
        val statusList = statusRepository.findAllHome()
        _uiState.update {
            it.copy(
                statusList = StatusConverter.convertToBindingModel(statusList)
            )
        }
    }

    fun onResume() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val me = getMeService.execute() ?: return@launch
            _uiState.update {
                it.copy(
                    bindingModel = ProfileConverter.convertToProfile(me)
                )
            }
            fetchHome()
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun onRefresh() {
        viewModelScope.launch {
            _uiState.update { it.copy(isRefreshing = true) }
            fetchHome()
            _uiState.update { it.copy(isRefreshing = false) }
        }
    }

    fun onClickEdit() {
        _navigateToEdit.value = Unit
    }

    fun onClickBack() {
        _goBack.value = Unit
    }
}
