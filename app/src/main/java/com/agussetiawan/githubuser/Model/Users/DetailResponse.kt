package com.agussetiawan.githubuser.Model.Users

data class DetailResponse(
    val login: String? = null,
    val name: String? = null,
    val type: String? = null,
    val avatar_url: String? = null,
    val bio: String? = null,
    val followers: Int? = null,
    val following: Int? = null,
    val company: String? = null,
    val location: String? = null,
    val public_repos: String? = null
)
