package com.example.capstoneprojectngirit.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstoneprojectngirit.api.ApiConfig
import com.example.capstoneprojectngirit.response.LoginResponse
import com.example.capstoneprojectngirit.response.LoginResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel:ViewModel() {
    private val _login = MutableLiveData<LoginResponse>()
    val login: LiveData<LoginResponse> = _login

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> =_isLoading

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    private val loginResponse=LoginResponse(success = false, message = "", userId = "", userName = "")

    fun login(email:String, password:String){
        _isLoading.value = true
        val client =ApiConfig().getApiService().login(email, password)
        client.enqueue(object: Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                _isLoading.value = false
                val responseBody = response.body()
                Log.d(TAG, "onResponse: ${responseBody}")
                if (response.isSuccessful){
                    _login.value = loginResponse
                    Log.d(TAG, responseBody?.message.toString())
                    Log.d(TAG,response.body()?.userId.toString())
                    Log.d(TAG,response.body()?.userName ?: "username")
                }
                if(!response.isSuccessful){
                    _toastMessage.value = response.message()
                }

            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
                _toastMessage.value = t.message
                Log.d(TAG,t.message.toString())

            }

        })
    }
    companion object{
        const val TAG = "extra_tag"
    }
}