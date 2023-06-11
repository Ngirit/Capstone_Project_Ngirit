package com.example.capstoneprojectngirit.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstoneprojectngirit.api.ApiConfig
import com.example.capstoneprojectngirit.response.MainResponse
import com.example.capstoneprojectngirit.user.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel:ViewModel() {
    private val _login = MutableLiveData<ArrayList<UserModel>>()
    val login:LiveData<ArrayList<UserModel>> = _login

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading:LiveData<Boolean> = _isLoading

    fun getUser(userId:String){
        _isLoading.value = true
        val client = ApiConfig().getApiService().getUser(userId)
        client.enqueue(object : Callback<MainResponse>{
            override fun onResponse(call: Call<MainResponse>, response: Response<MainResponse>) {
                _isLoading.value = false
                val responseBody = response.body()
                if (response.isSuccessful){
                    _login.postValue(responseBody?.user)
                    Log.d(TAG,responseBody?.user.toString())
                }
            }

            override fun onFailure(call: Call<MainResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG,t.message.toString())
            }

        })
    }

    companion object{
        const val TAG = "extra_tag"
    }
}