package com.example.todoproject

import android.content.Context
import android.content.SharedPreferences
import com.example.todoproject.model.UserData
import com.google.gson.Gson

object AuthPrefs {
  private  const val PREFS_NAME = "todo_app_prefs"
  private  const val KEY_IS_LOGGED_IN = "is_logged_in"
  private const val KEY_USERNAME = "username"
    private const val KEY_SHOW_ONBOARDING = "show_onboarding"


  private fun getPrefs(context: Context): SharedPreferences {
      return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
  }

     fun isLoggedIn(context : Context ): Boolean {
        return getPrefs(context).getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun saveLogin(context: Context, userData: UserData) {
        val gson = Gson()
        getPrefs(context).edit().apply {
            putBoolean(KEY_IS_LOGGED_IN, true)
            putString(KEY_USERNAME, gson.toJson(userData))
            apply()
        }
    }
    fun getUserData(context: Context): UserData? {
        val gson = Gson()
        val userDataJson = getPrefs(context).getString(KEY_USERNAME, null)
        return if(userDataJson != null){ gson.fromJson(userDataJson, UserData::class.java) } else { null }
    }

   fun  getAuthToken(context: Context): String {
       return getUserData(context)?.token ?: ""
   }



    fun getUsername(context: Context): String? {
        return getPrefs(context).getString(KEY_USERNAME, "") ?: ""
    }

    fun logout(context: Context) {
        getPrefs(context).edit().apply {
            putBoolean(KEY_IS_LOGGED_IN, false)
            remove(KEY_USERNAME)
            apply()
        }
    }

    fun shouldShowOnboarding(context: Context): Boolean {
        return getPrefs(context).getBoolean(KEY_SHOW_ONBOARDING, true)
    }

    fun setShowOnboardingStatus(context: Context) {
        getPrefs(context).edit().apply {
            putBoolean(KEY_SHOW_ONBOARDING, false)
            apply()
        }
    }












}