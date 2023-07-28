package com.dmm.bootcamp.yatter2023.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmm.bootcamp.yatter2023.domain.service.GetMeService
import com.dmm.bootcamp.yatter2023.usecase.update.UpdateCredentialsUseCase
import com.dmm.bootcamp.yatter2023.util.SingleLiveEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File

class EditViewModel(
    private val getMeService: GetMeService,
    private val updateCredentialsUseCase: UpdateCredentialsUseCase
): ViewModel() {
    private val _uiState: MutableStateFlow<EditUiState> = MutableStateFlow(EditUiState.empty())
    val uiState: StateFlow<EditUiState> = _uiState

    private val _goBack: SingleLiveEvent<Unit> = SingleLiveEvent()
    val goBack: LiveData<Unit> = _goBack

    fun onCreate() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val me = getMeService.execute() ?: return@launch
            val snapShotBindingModel = uiState.value.bindingModel
            _uiState.update {
                it.copy(
                    bindingModel = snapShotBindingModel.copy(
                        displayName = me.displayName ?: "",
                        note = me.note ?: "",
                        avatar = me.avatar.toString(),
                        header = me.header.toString()
                    )
                )
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun onClickCancel() {
        _goBack.value = Unit
    }

    fun onClickSave() {
        viewModelScope.launch {
            if (!uiState.value.canSave) return@launch
            val me = getMeService.execute() ?: return@launch
            val snapShotBindingModel = uiState.value.bindingModel
            updateCredentialsUseCase.execute(
                me = me,
                newDisplayName = snapShotBindingModel.displayName,
                newNote = snapShotBindingModel.note,
                newAvatar = snapShotBindingModel.uploadAvatar,
                newHeader = snapShotBindingModel.uploadHeader
            )
            _goBack.value = Unit
        }
    }

    fun onChangedDisplayName(displayName: String) {
        _uiState.update {
            it.copy(
                bindingModel = uiState.value.bindingModel.copy(displayName = displayName),
                canSave = true
            )
        }
    }

    fun onChangedNote(note: String) {
        _uiState.update {
            it.copy(
                bindingModel = uiState.value.bindingModel.copy(note = note),
                canSave = true
            )
        }
    }

    fun onChangedAvatar(avatarStr: String, avatarFile: File?) {
        _uiState.update {
            it.copy(
                bindingModel = uiState.value.bindingModel.copy(
                    avatar = avatarStr,
                    uploadAvatar = avatarFile,
                ),
                canSave = true
            )
        }
    }

    fun onChangedHeader(headerStr: String, headerFile: File?) {
        _uiState.update {
            it.copy(
                bindingModel = uiState.value.bindingModel.copy(
                    header = headerStr,
                    uploadHeader = headerFile
                ),
                canSave = true
            )
        }
    }
}
