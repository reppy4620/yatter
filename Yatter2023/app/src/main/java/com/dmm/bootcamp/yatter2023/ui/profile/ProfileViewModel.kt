package com.dmm.bootcamp.yatter2023.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmm.bootcamp.yatter2023.domain.repository.StatusRepository
import com.dmm.bootcamp.yatter2023.domain.service.GetMeService
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

    fun onCreate() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val me = getMeService.execute() ?: return@launch
            val snapShotBindingModel = uiState.value.bindingModel
            _uiState.update {
                it.copy(
                    bindingModel = snapShotBindingModel.copy(
                        username = me.username.value,
                        displayName = me.displayName ?: "Unknown",  // TODO: Delete placeholder
                        note = me.note ?: "",
                        avatar = me.avatar.toString(),
                        header = me.header.toString()
                    )
                )
            }

            val statusList = statusRepository.findAllHome()
            _uiState.update {
                it.copy(
                    statusList = StatusConverter.convertToBindingModel(statusList)
                )
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun onClickEdit() {
        _navigateToEdit.value = Unit
    }
}
