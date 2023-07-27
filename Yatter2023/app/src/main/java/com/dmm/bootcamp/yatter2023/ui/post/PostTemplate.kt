package com.dmm.bootcamp.yatter2023.ui.post

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dmm.bootcamp.yatter2023.R
import com.dmm.bootcamp.yatter2023.ui.theme.Pink80
import com.dmm.bootcamp.yatter2023.ui.theme.Purple40
import com.dmm.bootcamp.yatter2023.ui.theme.PurpleGrey40
import com.dmm.bootcamp.yatter2023.ui.theme.PurpleGrey80
import com.dmm.bootcamp.yatter2023.ui.theme.Yatter2023Theme

@Composable
fun PostTemplate(
    postBindingModel: PostBindingModel,
    isLoading: Boolean,
    canPost: Boolean,
    onStatusTextChanged: (String) -> Unit,
    onClickPost: () -> Unit,
    onClickNavIcon: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.post_topbar))
                },
                navigationIcon = {
                    IconButton(onClick = onClickNavIcon) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.post_topbar_icon_desc)
                        )
                    }
                },
                actions = {
                    OutlinedButton(
                        onClick = onClickPost,
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            backgroundColor = Purple40,
                        ),
                        border = BorderStroke(1.dp, Color.White),
                        shape = RoundedCornerShape(50.dp),
                        enabled = canPost
                    ) {
                        Text(
                            text = stringResource(id = R.string.post_button),
                        )
                    }
                }
            )
        },
        
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(it)) {
                AsyncImage(
                    modifier = Modifier.size(64.dp),
                    model = postBindingModel.avatarUrl,
                    contentDescription = stringResource(id = R.string.post_avatar_desc),
                    contentScale = ContentScale.Crop
                )
                Column(horizontalAlignment = Alignment.End) {
                    TextField(
                        modifier = Modifier
                            .focusRequester(focusRequester)
                            .fillMaxWidth()
                            .weight(1f),
                        value = postBindingModel.statusText,
                        onValueChange = onStatusTextChanged,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        placeholder = {
                            Text(text = stringResource(id = R.string.post_placeholder))
                        },
                    )
                }
            }
            if (isLoading) {
                CircularProgressIndicator()
            }
        }
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}


@Preview
@Composable
private fun PostTemplatePreview() {
    Yatter2023Theme {
        Surface {
            PostTemplate(
                postBindingModel = PostBindingModel(
                    avatarUrl = null,
                    statusText = ""
                ),
                isLoading = false,
                canPost = false,
                onStatusTextChanged = {},
                onClickPost = {},
                onClickNavIcon = {}
            )
        }
    }
}
