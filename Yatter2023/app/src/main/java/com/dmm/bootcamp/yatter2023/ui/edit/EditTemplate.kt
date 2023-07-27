package com.dmm.bootcamp.yatter2023.ui.edit

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
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dmm.bootcamp.yatter2023.R
import com.dmm.bootcamp.yatter2023.ui.theme.Yatter2023Theme


@Composable
fun EditTemplate(
    displayName: String,
    note: String,
    avatar: String,
    header: String,
    onClickCancel: () -> Unit,
    onClickSave: () -> Unit,
    onChangedDisplayName: (String) -> Unit,
    onChangedNote: (String) -> Unit,
    onChangedAvatar: (String) -> Unit,
    onChangedHeader: (String) -> Unit,
) {
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
                    ) {
                        Text(
                            text = stringResource(id = R.string.edit_save),
                            color = Color.White,
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
                    model = header,
                    contentDescription = "header",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(100.dp),
                    contentScale = ContentScale.Crop
                )
                Divider(thickness = 1.dp)
                Spacer(modifier = Modifier.height(5.dp))
                Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                    AsyncImage(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                        model = avatar,
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