package com.dmm.bootcamp.yatter2023.ui.timeline

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dmm.bootcamp.yatter2023.ui.theme.Yatter2023Theme
import com.dmm.bootcamp.yatter2023.ui.timeline.bindingmodel.MediaBindingModel
import com.dmm.bootcamp.yatter2023.ui.timeline.bindingmodel.StatusBindingModel

@Composable
fun StatusRow(
    statusBindingModel: StatusBindingModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(64.dp)
                .padding(2.dp)
                .clip(CircleShape),
            model = statusBindingModel.avatar,
            contentDescription = "avatar image",
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(start = 8.dp, end = 15.dp)) {
            Row {
                Text(text = statusBindingModel.displayName, fontWeight = FontWeight.Bold)
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(text = "@${statusBindingModel.username}")
                }
            }
            Text(text = statusBindingModel.content)
            Spacer(modifier = Modifier.padding(vertical = 5.dp))
            if (statusBindingModel.attachmentMediaList.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .horizontalScroll(rememberScrollState())
                ) {
                    statusBindingModel.attachmentMediaList.map {
                        StatusImage(model = it.url, contentDescription = it.description)
                    }
                }
            }

        }
    }

}

@Composable
fun StatusImage(
    model: String,
    contentDescription: String,
) {
    AsyncImage(
        model = model,
        contentDescription = contentDescription,
        modifier = Modifier
            .size(150.dp)
            .padding(3.dp)
            .clip(RoundedCornerShape(10.dp)),
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
private fun StatusRowPreview() {
    Yatter2023Theme {
        Surface {
            StatusRow(
                statusBindingModel = StatusBindingModel(
                    id = "id",
                    displayName = "reppy",
                    username = "reppy4620",
                    avatar = "https://avatars.githubusercontent.com/u/19385268?v=4",
                    content = "preview content",
                    attachmentMediaList = listOf(
                        MediaBindingModel(
                            id = "id",
                            type = "image",
                            url = "https://avatars.githubusercontent.com/u/39693306?v=4",
                            description = "icon"
                        ),
                        MediaBindingModel(
                            id = "id",
                            type = "image",
                            url = "https://pbs.twimg.com/profile_banners/972404402425245697/1690337648/1500x500",
                            description = "icon"
                        ),
                        MediaBindingModel(
                            id = "id",
                            type = "image",
                            url = "https://avatars.githubusercontent.com/u/39693306?v=4",
                            description = "icon"
                        ),
                    )
                ),
                modifier = Modifier
            )
        }
    }
}