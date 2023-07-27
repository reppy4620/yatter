package com.dmm.bootcamp.yatter2023.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dmm.bootcamp.yatter2023.usecase.update.UpdateCredentialsUseCase
import com.dmm.bootcamp.yatter2023.util.SingleLiveEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class EditViewModel(
    private val updateCredentialsUseCase: UpdateCredentialsUseCase
): ViewModel() {
    private val _uiState: MutableStateFlow<EditUiState> = MutableStateFlow(EditUiState.empty())
    val uiState: StateFlow<EditUiState> = _uiState

    private val _goBack: SingleLiveEvent<Unit> = SingleLiveEvent()
    val goBack: LiveData<Unit> = _goBack

    fun onClickCancel() {
        _goBack.value = Unit
    }

    fun onClickSave() {
        _goBack.value = Unit
    }

    fun onChangedDisplayName(displayName: String) {
        _uiState.update {
            it.copy(
                bindingModel = uiState.value.bindingModel.copy(displayName = displayName)
            )
        }
    }

    fun onChangedNote(note: String) {
        _uiState.update {
            it.copy(
                bindingModel = uiState.value.bindingModel.copy(note = note)
            )
        }
    }

    fun onChangedAvatar(avatar: String) {

    }

    fun onChangedHeader(header: String) {

    }
}
