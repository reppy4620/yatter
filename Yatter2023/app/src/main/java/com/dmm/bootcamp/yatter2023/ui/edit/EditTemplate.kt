package com.dmm.bootcamp.yatter2023.ui.edit

import android.webkit.URLUtil
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dmm.bootcamp.yatter2023.R
import com.dmm.bootcamp.yatter2023.ui.component.FullScreenLoadingIndicator
import com.dmm.bootcamp.yatter2023.ui.theme.Yatter2023Theme
import java.io.File


@Composable
fun EditTemplate(
    displayName: String,
    note: String,
    avatar: String,
    header: String,
    canSave: Boolean,
    isLoading: Boolean,
    onClickCancel: () -> Unit,
    onClickSave: () -> Unit,
    onChangedDisplayName: (String) -> Unit,
    onChangedNote: (String) -> Unit,
    onChangedAvatar: (File?) -> Unit,
    onChangedHeader: (File?) -> Unit,
) {

    var avatarUri by rememberSaveable {
        mutableStateOf(avatar)
    }
    var headerUri by rememberSaveable {
        mutableStateOf(header)
    }

    val context = LocalContext.current
    val avatarLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) {
        avatarUri = it.toString()
        val inputStream = it?.let { context.contentResolver.openInputStream(it) } ?: return@rememberLauncherForActivityResult
        val imageFile = File.createTempFile("upload", "tmp_avatar", context.cacheDir).apply {
            outputStream().use { fileOutputStream -> inputStream.copyTo(fileOutputStream) }
        }
        onChangedAvatar(imageFile)
    }
    val headerLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) {
        headerUri = it.toString()
        val inputStream = it?.let { context.contentResolver.openInputStream(it) } ?: return@rememberLauncherForActivityResult
        val imageFile = File.createTempFile("upload", "tmp_header", context.cacheDir).apply {
            outputStream().use { fileOutputStream -> inputStream.copyTo(fileOutputStream) }
        }
        onChangedHeader(imageFile)
    }

    Scaffold(
        topBar = {
            TopAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(
                        onClick = onClickCancel,
                    ) {
                        Text(
                            text = stringResource(id = R.string.edit_cancel),
                            color = Color.White,
                        )
                    }
                    Text(
                        text = stringResource(id = R.string.edit_title),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp),
                        fontSize = 25.sp
                    )
                    TextButton(
                        onClick = onClickSave,
                        enabled = canSave
                    ) {
                        Text(
                            text = stringResource(id = R.string.edit_save),
                            color = if (canSave) Color.White else Color.Gray,
                        )
                    }
                }
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            Column {
                AsyncImage(
                    model = headerUri,
                    contentDescription = "header",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(100.dp)
                        .clickable {
                            headerLauncher.launch(
                                PickVisualMediaRequest(
                                    mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        },
                    contentScale = ContentScale.Crop
                )
                Divider(thickness = 1.dp)
                Spacer(modifier = Modifier.height(5.dp))
                Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                    AsyncImage(
                        model = avatarUri,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .border(BorderStroke(1.dp, Color.Gray), CircleShape)
                            .clickable {
                                avatarLauncher.launch(
                                    PickVisualMediaRequest(
                                        mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                                    )
                                )
                            },
                        contentDescription = "avatar image",
                        contentScale = ContentScale.Crop
                    )
                    Divider(thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))
                    OutlinedTextField(
                        value = displayName,
                        maxLines = 1,
                        onValueChange = onChangedDisplayName,
                        label = {
                            Text(text = stringResource(id = R.string.edit_display_name))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    )
                    Divider(thickness = 1.dp, modifier = Modifier.padding(vertical = 5.dp))
                    OutlinedTextField(
                        value = note,
                        maxLines = 3,
                        onValueChange = onChangedNote,
                        label = {
                            Text(text = stringResource(id = R.string.edit_note))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    )
                    Divider(thickness = 1.dp, modifier = Modifier.padding(vertical = 5.dp))
                }
            }
        }
        if (isLoading) {
            FullScreenLoadingIndicator()
        }
    }
}

@Preview
@Composable
private fun EditTemplatePreview() {
    Yatter2023Theme {
        Surface {
            EditTemplate(
                displayName = "reppy",
                note = "Hello, World",
                avatar = stringResource(id = R.string.profile_sample_avatar),
                header = stringResource(id = R.string.profile_sample_header),
                canSave = false,
                isLoading = true,
                onClickCancel = {},
                onClickSave = {},
                onChangedDisplayName = {},
                onChangedNote = {},
                onChangedAvatar = {},
                onChangedHeader = {},
            )
        }
    }
}