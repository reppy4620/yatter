package com.dmm.bootcamp.yatter2023.ui.timeline.drawer

import android.webkit.URLUtil
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
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
fun DrawerContent(
    username: String,
    displayName: String,
    avatar: String?,
    followingCount: Int,
    followerCount: Int,
    onClickProfile: () -> Unit
) {
    // TODO: Delete placeholder
    val displayName = displayName.ifEmpty { stringResource(id = R.string.profile_sample_display_name) }
    val avatar = if (URLUtil.isValidUrl(avatar)) avatar else stringResource(id = R.string.profile_sample_avatar)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    ) {
        Column {
            Column(modifier = Modifier.padding(start = 20.dp)) {
                AsyncImage(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape),
                    model = avatar,
                    contentDescription = "avatar image",
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = displayName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(text = "@${username}")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Text(text = "$followingCount", fontWeight = FontWeight.Bold, modifier = Modifier.padding(end = 2.dp))
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(text = "following")
                    }
                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                    Text(text = "$followerCount", fontWeight = FontWeight.Bold, modifier = Modifier.padding(end = 2.dp))
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(text = "followers")
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            DrawerItem(
                icon = Icons.Default.Person,
                text = stringResource(id = R.string.drawer_profile),
                onClick = onClickProfile
            )
        }
    }
}

@Composable
private fun DrawerItem(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(20.dp),
    ) {
        Icon(icon, "Profile")
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 20.sp
        )
    }
}

@Preview
@Composable
private fun DrawerContentPreview() {
    Yatter2023Theme {
        Surface {
            DrawerContent(
                username = "username",
                displayName = "displayName",
                avatar = "https://avatars.githubusercontent.com/u/19385268?v=4",
                followingCount = 0,
                followerCount = 0,
                onClickProfile = {}
            )
        }
    }
}