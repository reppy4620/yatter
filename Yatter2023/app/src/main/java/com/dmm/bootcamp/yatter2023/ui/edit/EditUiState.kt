package com.dmm.bootcamp.yatter2023.ui.edit

data class EditUiState(
    val bindingModel: EditBindingModel,
    val isLoading: Boolean,
    val canSave: Boolean
) {

    companion object {
        fun empty(): EditUiState = EditUiState(
            bindingModel = EditBindingModel(
                displayName = "",
                note = "",
                avatar = "",
                header = "",
                uploadAvatar = null,
                uploadHeader = null
            ),
            isLoading = false,
            canSave = false
        )
    }
}