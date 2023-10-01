package com.agussetiawan.githubuser.Model.Views

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agussetiawan.githubuser.API.Client
import com.agussetiawan.githubuser.Model.Users.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowView: ViewModel() {
    private val follow = MutableLiveData<ArrayList<Users>>()
    val followLive: LiveData<ArrayList<Users>> = follow

    fun setFollowers(username: String) {
        val client = Client.apiInstance.getFollowersUsers(username)

        client.enqueue(object : Callback<ArrayList<Users>> {
            override fun onResponse(
                call: Call<ArrayList<Users>>,
                response: Response<ArrayList<Users>>
            ) {
                if (response.isSuccessful) {
                    follow.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ArrayList<Users>>, t: Throwable) {
                Log.e("FollowViewModel", "onFailure setFollowers ${t.message}")
            }
        })
    }

    fun setFollowing(username: String) {
        val client = Client.apiInstance.getFollowingUsers(username)

        client.enqueue(object : Callback<ArrayList<Users>> {
            override fun onResponse(
                call: Call<ArrayList<Users>>,
                response: Response<ArrayList<Users>>
            ) {
                if (response.isSuccessful) {
                    follow.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ArrayList<Users>>, t: Throwable) {
                Log.e("FollowViewModel", "onFailure setFollowers ${t.message}")
            }
        })
    }
}