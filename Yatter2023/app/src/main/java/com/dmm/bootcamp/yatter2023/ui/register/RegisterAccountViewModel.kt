package com.dmm.bootcamp.yatter2023.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmm.bootcamp.yatter2023.domain.model.Password
import com.dmm.bootcamp.yatter2023.domain.model.Username
import com.dmm.bootcamp.yatter2023.infra.domain.service.LoginServiceImpl
import com.dmm.bootcamp.yatter2023.usecase.impl.login.LoginUseCaseImpl
import com.dmm.bootcamp.yatter2023.usecase.login.LoginUseCase
import com.dmm.bootcamp.yatter2023.usecase.login.LoginUseCaseResult
import com.dmm.bootcamp.yatter2023.usecase.register.RegisterAccountUseCase
import com.dmm.bootcamp.yatter2023.usecase.register.RegisterAccountUseCaseResult
import com.dmm.bootcamp.yatter2023.util.SingleLiveEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterAccountViewModel(
    private val registerAccountUseCase: RegisterAccountUseCase,
    private val loginUseCase: LoginUseCase
): ViewModel() {
    private val _uiState: MutableStateFlow<RegisterAccountUiState> = MutableStateFlow(RegisterAccountUiState.empty())
    val uiState: StateFlow<RegisterAccountUiState> = _uiState

    private val _navigateToLogin: SingleLiveEvent<Unit> = SingleLiveEvent()
    val navigateToLogin: LiveData<Unit> = _navigateToLogin

    private val _goBack: SingleLiveEvent<Unit> = SingleLiveEvent()
    val goBack: LiveData<Unit> = _goBack

    private val _navigateToTimeline: SingleLiveEvent<Unit> = SingleLiveEvent()
    val navigateToTimeline = _navigateToTimeline

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

                }
                is RegisterAccountUseCaseResult.Failure -> {
                    _goBack.value = Unit
                }
            }
            when(
                val result =
                    loginUseCase.execute(
                        username = Username(snapshotBindingModel.username),
                        password = Password(snapshotBindingModel.password)
                    )
            ) {
                is LoginUseCaseResult.Success -> {
                    _navigateToTimeline.value = Unit
                }
                else -> {

                }
            }
        }
    }

    fun onClickNavIcon() {
        _goBack.value = Unit
    }
}