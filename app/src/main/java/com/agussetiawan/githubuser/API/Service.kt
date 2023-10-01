package com.agussetiawan.githubuser.API

import com.agussetiawan.githubuser.Model.Users.DetailResponse
import com.agussetiawan.githubuser.Model.Users.Response
import com.agussetiawan.githubuser.Model.Users.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {
    @GET("search/users")

    fun getSearchUsers(
        @Query("q") query: String,
    ): Call<Response>

    @GET("users/{username}")

    fun getDetailUsers(
        @Path("username") username: String,
    ): Call<DetailResponse>

    @GET("users/{username}/following")

    fun getFollowingUsers(
        @Path("username") username: String,
    ): Call<ArrayList<Users>>

    @GET("users/{username}/followers")

    fun getFollowersUsers(
        @Path("username") username: String,
    ): Call<ArrayList<Users>>
}