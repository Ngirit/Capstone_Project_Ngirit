package com.example.capstoneprojectngirit.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstoneprojectngirit.api.ApiConfig
import com.example.capstoneprojectngirit.response.Data
import com.example.capstoneprojectngirit.response.RegisterResponseCadangan
import com.example.capstoneprojectngirit.response.RegisteringResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel:ViewModel() {
    private val _data = MutableLiveData<Data>()
    val data:LiveData<Data> = _data

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun register(username:String,email:String,password:String){
        _isLoading.value = true
        val client = ApiConfig().getApiService().createAccount(username, email, password)

        client.enqueue(object : Callback<RegisteringResponse>{
            override fun onResponse(
                call: Call<RegisteringResponse>,
                response: Response<RegisteringResponse>
            ) {
                _isLoading.value = false
                val responseBody = response.body()
                Log.d(TAG, "onResponse: ${responseBody}")
                if (response.isSuccessful && responseBody?.message == "Account Created"){
                    Log.d(TAG, responseBody?.message.toString())
                    _data.value = responseBody?.data

                    Log.d(TAG, responseBody?.data?.idUser.toString())
                    Log.d(TAG, responseBody?.data?.username ?: "username")
                    Log.d(TAG, responseBody?.data?.email ?: "email")
                    Log.d(TAG, responseBody?.data?.password ?: "password")
                }
            }

            override fun onFailure(call: Call<RegisteringResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG,t.message.toString())
            }

        })
    }

    companion object{
        const val TAG ="extra_tag"
    }
}