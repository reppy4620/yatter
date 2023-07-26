package com.dmm.bootcamp.yatter2023.ui.register_account

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun RegisterAccountPage(viewModel: RegisterAccountViewModel) {
    val uiState: RegisterAccountUiState by viewModel.uiState.collectAsStateWithLifecycle()

    RegisterAccountTemplate(
        username = uiState.registerAccountBindingModel.username,
        onChangedUsername = viewModel::onChangedUsername,
        password = uiState.registerAccountBindingModel.password,
        onChangedPassword = viewModel::onChangedPassword,
        isEnableRegister = uiState.isEnableRegister,
        isLoading = uiState.isLoading,
        onClickRegister = viewModel::onClickRegister,
        onClickNavIcon = viewModel::onClickNavIcon
    )
}