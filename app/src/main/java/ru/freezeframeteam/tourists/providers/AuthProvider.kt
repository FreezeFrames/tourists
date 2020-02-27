package ru.freezeframeteam.tourists.providers

import android.util.Log
import okhttp3.*
import okhttp3.MultipartBody
import ru.freezeframeteam.tourists.Constants
import ru.services.vertex.social.Models.UserModels.UserModelForLogin
import ru.services.vertex.social.Models.UserModels.UserModelForReg


class AuthProvider {

    companion object {

        fun loginRequest (callback : Callback, userModel : UserModelForLogin): Call {
            val localhost_ = "192.168.4.34"
            // идет работа с сетью
            val client = OkHttpClient()

            val httpUrl = HttpUrl.Builder()
                .scheme("http")
                .host(localhost_)
                .addPathSegment("Consierge-Backend")
                .addPathSegment("public")
                .addPathSegment("api")
                .addPathSegment("code")
                .addQueryParameter(
                    "phone_number",
                    userModel.phone
                ).build()
            val request = Request.Builder()
                .url(httpUrl)
                .get()
                .build()

            val call = client.newCall(request)
            call.enqueue(callback)
            return call
        }

        fun registrationRequest (callback : Callback, userModel : UserModelForReg): Call {
            // идет работа с сетью
            val client = OkHttpClient()
            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("username", userModel.username)
                .addFormDataPart("email", userModel.email)
                .addFormDataPart("password", userModel.pass)
                .build()
            val request = Request.Builder()
                .url(Constants.ApiKeyRegistration)
                .post(requestBody)
                .build()
            val call = client.newCall(request)
            call.enqueue(callback)
            return call
        }
    }

}