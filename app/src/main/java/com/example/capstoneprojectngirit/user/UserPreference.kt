package com.example.capstoneprojectngirit.user

import android.content.Context

class UserPreference (context:Context){
    val preference = context.getSharedPreferences(LOGIN_PREFERENCE,Context.MODE_PRIVATE)

    fun setUser(user:UserModel){
        val prefEdit=preference.edit()
        prefEdit.putString(NAME,user.name)
        prefEdit.putString(USER_ID,user.userId)
        prefEdit.putBoolean(LOGIN_STATE,user.isLogin)
        prefEdit.apply()
    }

    fun getUser():UserModel{
        return UserModel(
            preference.getString(NAME,"")?:"",
            preference.getString(USER_ID,"")?:"",
            preference.getBoolean(LOGIN_STATE,false)

        )
    }

    fun logout(){
        val prefEdit = preference.edit()
        prefEdit.remove(NAME)
        prefEdit.remove(USER_ID)
        prefEdit.putBoolean(LOGIN_STATE,false)
        prefEdit.apply()
    }

    companion object {
        const val LOGIN_PREFERENCE = "login"
        const val NAME = "name"
        const val USER_ID = "user_id"
        const val LOGIN_STATE = "login_state"
    }
}