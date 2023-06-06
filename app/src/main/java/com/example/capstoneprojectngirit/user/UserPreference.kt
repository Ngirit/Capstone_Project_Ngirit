package com.example.capstoneprojectngirit.user

import android.content.Context

class UserPreference (context:Context){
    val preference = context.getSharedPreferences(LOGIN_PREFERENCE,Context.MODE_PRIVATE)

    fun setUser(user:UserModel){
        val prefEdit=preference.edit()
        prefEdit.putString(NAME,user.name)
        prefEdit.putString(TOKEN,user.token)
        prefEdit.putBoolean(LOGIN_STATE,user.isLogin)
        prefEdit.apply()
    }

    fun getUser():UserModel{
        return UserModel(
            preference.getString(NAME,"")?:"",
            preference.getString(TOKEN,"")?:"",
            preference.getBoolean(LOGIN_STATE,false)

        )
    }

    fun logout(){
        val prefEdit = preference.edit()
        prefEdit.remove(NAME)
        prefEdit.remove(TOKEN)
        prefEdit.putBoolean(LOGIN_STATE,false)
        prefEdit.apply()
    }

    companion object {
        const val LOGIN_PREFERENCE = "login"
        const val NAME = "name"
        const val TOKEN = "token"
        const val LOGIN_STATE = "login_state"
    }
}