package com.dmm.bootcamp.yatter2023.ui.register_account

data class RegisterAccountUiState(
    val registerAccountBindingModel: RegisterAccountBindingModel,
    val isLoading: Boolean,
    val validUsername: Boolean,
    val validPassword: Boolean
) {
    val isEnableRegister = validUsername && validPassword

    companion object {
        fun empty(): RegisterAccountUiState = RegisterAccountUiState(
            registerAccountBindingModel = RegisterAccountBindingModel(
                username = "",
                password = ""
            ),
            isLoading = false,
            validUsername = false,
            validPassword = false
        )
    }
}
