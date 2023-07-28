package com.dmm.bootcamp.yatter2023.ui.login

data class LoginUiState(
    val loginBindingModel: LoginBindingModel,
    val isLoading: Boolean,
    val validUsername: Boolean,
    val validPassword: Boolean,
    val passwordVisible: Boolean
) {
    val isEnableLogin: Boolean = validUsername && validPassword

    companion object {
        fun empty(): LoginUiState = LoginUiState(
            loginBindingModel = LoginBindingModel("", ""),
            isLoading = false,
            validUsername = false,
            validPassword = false,
            passwordVisible = false
        )
    }
}