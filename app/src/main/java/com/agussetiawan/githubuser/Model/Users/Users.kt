package com.agussetiawan.githubuser.Model.Users

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(
    val id: Int? = null,
    val login: String? = null,
    val type: String? = null,
    val avatar_url: String? = null
) : Parcelable