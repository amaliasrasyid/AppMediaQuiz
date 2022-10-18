package com.kontrakanprojects.appgamequiz.data.session

import android.content.Context
import com.kontrakanprojects.appgamequiz.data.model.User

class UserPreference(context: Context) {
    companion object{
        private const val PREFS_NAME = "prefs_name"
        private const val ID = "id"
        private const val NAME = "name"
        private const val PASSWORD = "password"
        private const val ROLE = "role"
        private const val LOGIN = "login"
    }

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setUser(data: User){
        val editor = prefs.edit()
        editor.putInt(ID, data.id!!)
        editor.putString(NAME, data.name)
        editor.putString(PASSWORD, data.password)
        editor.putInt(ROLE, data.role!!)
        editor.apply()
    }

    fun getUser(): User =
        User(
            id = prefs.getInt(ID, 0),
            name = prefs.getString(NAME, "").toString(),
            password = prefs.getString(PASSWORD, "").toString(),
            role = prefs.getInt(ROLE, 0)
        )


    fun setLogin(value: Boolean){
        val editor = prefs.edit()
        editor.putBoolean(LOGIN, value)
        editor.apply()
    }

    fun getLogin(): Boolean =
        prefs.getBoolean(LOGIN, false)

    fun logout(){
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }
}