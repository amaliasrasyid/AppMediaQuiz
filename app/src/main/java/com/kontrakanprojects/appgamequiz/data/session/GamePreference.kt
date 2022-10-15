package com.kontrakanprojects.appgamequiz.data.session

import android.content.Context
import com.kontrakanprojects.appgamequiz.data.model.User

class GamePreference(context: Context) {
    companion object{
        private const val PREFS_NAME = "game_prefs"
        private const val GAME_STATE = "game_state"
    }

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)


    fun setGameState(value: Boolean){
        val editor = prefs.edit()
        editor.putBoolean(GAME_STATE, value)
        editor.apply()
    }

    fun getGameState(): Boolean =
        prefs.getBoolean(GAME_STATE, false)

}