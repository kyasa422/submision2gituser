package com.agussetiawan.githubuser.Model.Views

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agussetiawan.githubuser.API.Client
import com.agussetiawan.githubuser.Model.Users.DetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailView: ViewModel() {
    private val userDetail = MutableLiveData<DetailResponse>()
    val userDetailLive: LiveData<DetailResponse> = userDetail

    fun setUserDetail(username: String) {
        val client = Client.apiInstance.getDetailUsers(username)

        client.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                if(response.isSuccessful){
                    userDetail.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.e("DetailViewModel", "onFailure setUserDetail ${t.message}")
            }
        })
    }
}