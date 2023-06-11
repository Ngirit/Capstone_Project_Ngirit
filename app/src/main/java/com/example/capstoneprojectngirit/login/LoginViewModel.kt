package com.example.capstoneprojectngirit.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capstoneprojectngirit.api.ApiConfig
import com.example.capstoneprojectngirit.response.LoginResponse
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

    fun login(email:String, password:String){
        _isLoading.value = true
        val client =ApiConfig().getApiService().login(email, password)
        client.enqueue(object: Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                _isLoading.value = false
                if (response.isSuccessful && response.body()?.message == "Login sukses"){
                    Log.d(TAG, response.body()?.message.toString())
                    Log.d(TAG,response.body()?.userId ?: "userId")
                    Log.d(TAG,response.body()?.userName ?: "username")
                }

            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG,t.message.toString())
            }

        })
    }
    companion object{
        const val TAG = "extra_tag"
    }
}