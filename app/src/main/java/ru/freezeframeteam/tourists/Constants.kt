package ru.freezeframeteam.tourists

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull

class Constants {

    companion object{
        val ApiKey : String = "https://vchat.dev.ompr.io/api/"
        val ApiKeyRegistration : String = ApiKey + "oauth/signup"
        val ApiKeyLogin : String = ApiKey + "oauth/signin"

        val USER_ACCESS_TOKEN = "USER_ACCESS_TOKEN"
        val APP_PREFERENCES = "my_settings"
        val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()

    }

}