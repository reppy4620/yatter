package com.dmm.bootcamp.yatter2023.ui.profile.bindingmodel.converter

import com.dmm.bootcamp.yatter2023.domain.model.Me
import com.dmm.bootcamp.yatter2023.ui.profile.bindingmodel.ProfileBindingModel

object ProfileConverter {
    fun convertToProfile(me: Me): ProfileBindingModel = ProfileBindingModel(
        username = me.username.value,
        displayName = me.displayName ?: "",
        note = me.note ?: "",
        avatar = me.avatar.toString(),
        header = me.header.toString(),
        followingCount = me.followingCount,
        followerCount = me.followerCount
    )
}