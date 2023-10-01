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

class UsersView: ViewModel() {
    private val _users = MutableLiveData<ArrayList<Users>>()
    val users: LiveData<ArrayList<Users>> = _users

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun searchUsers(query: String) {
        _isLoading.value = true
        val client = Client.apiInstance.getSearchUsers(query)

        client.enqueue(object : Callback<com.agussetiawan.githubuser.Model.Users.Response> {
            override fun onResponse(
                call: Call<com.agussetiawan.githubuser.Model.Users.Response>,
                response: Response<com.agussetiawan.githubuser.Model.Users.Response>
            ) {
                if (response.isSuccessful) {
                    _users.postValue(response.body()?.items)
                }

                _isLoading.value = false
            }

            override fun onFailure(call: Call<com.agussetiawan.githubuser.Model.Users.Response>, t: Throwable) {
                _isLoading.value = false
                Log.e("MainViewModel", "onFailure searchUsers ${t.message}")
            }
        })
    }
}