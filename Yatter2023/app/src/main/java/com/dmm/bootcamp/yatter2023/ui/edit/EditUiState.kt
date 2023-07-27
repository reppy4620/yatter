package com.dmm.bootcamp.yatter2023.ui.edit

data class EditUiState(
    val bindingModel: EditBindingModel,
    val canSave: Boolean
) {

    companion object {
        fun empty(): EditUiState = EditUiState(
            bindingModel = EditBindingModel(
                displayName = "",
                note = "",
                avatar = "",
                header = ""
            ),
            canSave = false
        )
    }
}