package com.dmm.bootcamp.yatter2023.ui.register_account

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmm.bootcamp.yatter2023.domain.model.Password
import com.dmm.bootcamp.yatter2023.domain.model.Username
import com.dmm.bootcamp.yatter2023.usecase.register.RegisterAccountUseCase
import com.dmm.bootcamp.yatter2023.usecase.register.RegisterAccountUseCaseResult
import com.dmm.bootcamp.yatter2023.util.SingleLiveEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterAccountViewModel(
    private val registerAccountUseCase: RegisterAccountUseCase
): ViewModel() {
    private val _uiState: MutableStateFlow<RegisterAccountUiState> = MutableStateFlow(RegisterAccountUiState.empty())
    val uiState: StateFlow<RegisterAccountUiState> = _uiState

    private val _navigateToLogin: SingleLiveEvent<Unit> = SingleLiveEvent()
    val navigateToLogin: LiveData<Unit> = _navigateToLogin

    private val _goBack: SingleLiveEvent<Unit> = SingleLiveEvent()
    val goBack: LiveData<Unit> = _goBack

    fun onChangedUsername(username: String) {
        val snapshotBindingModel = uiState.value.registerAccountBindingModel
        _uiState.update {
            it.copy(
                validUsername = Username(username).validate(),
                registerAccountBindingModel = snapshotBindingModel.copy(
                    username = username
                )
            )
        }
    }

    fun onChangedPassword(password: String) {
        val snapshotBindingModel = uiState.value.registerAccountBindingModel
        _uiState.update {
            it.copy(
                validPassword = Password(password).validate(),
                registerAccountBindingModel = snapshotBindingModel.copy(
                    password = password
                )
            )
        }
    }

    fun onClickRegister() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val snapshotBindingModel = uiState.value.registerAccountBindingModel
            when (
                val result =
                    registerAccountUseCase.execute(
                        username = snapshotBindingModel.username,
                        password = snapshotBindingModel.password
                    )
            ) {
                is RegisterAccountUseCaseResult.Success -> {
                    _navigateToLogin.value = Unit
                }
                is RegisterAccountUseCaseResult.Failure -> {

                }
            }
        }
    }

    fun onClickNavIcon() {
        _goBack.value = Unit
    }
}