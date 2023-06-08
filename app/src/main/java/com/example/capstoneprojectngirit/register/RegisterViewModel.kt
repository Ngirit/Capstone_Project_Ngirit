package com.example.capstoneprojectngirit.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstoneprojectngirit.api.ApiConfig
import com.example.capstoneprojectngirit.response.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel:ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun register(username:String,email:String,password:String){
        _isLoading.value = true
        val client = ApiConfig().getApiService().createAccount(username, email, password)

        client.enqueue(object : Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful && response.body()?.message == "Account Created")
                   Log.d(TAG,response.body()?.message.toString())
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG,t.message.toString())
            }

        })
    }

    companion object{
        const val TAG ="extra_tag"
    }
}