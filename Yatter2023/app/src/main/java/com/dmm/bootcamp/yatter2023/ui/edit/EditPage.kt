package com.dmm.bootcamp.yatter2023.ui.edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun EditPage(viewModel: EditViewModel) {
    val uiState: EditUiState by viewModel.uiState.collectAsStateWithLifecycle()

    EditTemplate(
        displayName = uiState.bindingModel.displayName,
        note = uiState.bindingModel.note,
        avatar = uiState.bindingModel.avatar,
        header = uiState.bindingModel.header,
        onClickCancel = viewModel::onClickCancel,
        onClickSave = viewModel::onClickSave,
        onChangedDisplayName = viewModel::onChangedDisplayName,
        onChangedNote = viewModel::onChangedNote,
        onChangedAvatar = viewModel::onChangedAvatar,
        onChangedHeader = viewModel::onChangedHeader
    )
}