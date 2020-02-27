package ru.freezeframeteam.tourists.presenters

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.navigation.NavController
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import ru.freezeframeteam.tourists.Constants
import ru.freezeframeteam.tourists.providers.AuthProvider
import ru.freezeframeteam.tourists.views.AuthView
import ru.services.vertex.social.Models.UserModels.UserModelForLogin
import ru.services.vertex.social.Models.UserModels.UserModelForReg
import java.io.IOException


@InjectViewState
class AuthPresenter : MvpPresenter<AuthView>() {
    lateinit var mSettings: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    fun login(userModel: UserModelForLogin, context : Context) {
        viewState.startAction()
        // ползем в провайдер
        GlobalScope.launch {
            AuthProvider.loginRequest(callback = object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    viewState.onError(1)
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
//                        val responseStr = response.body!!.string()
//                        val myJson = JSONObject(responseStr)
//                        val arr = myJson.getJSONObject("data")
//                        val tokenType = arr.getString("token_type")
//                        if(tokenType == "Bearer"){
//                            val accessToken = arr.getString("access_token")
//                            saveToken(accessToken, context)
//                            viewState.onSuccess()
//                        }
                        Log.d("12345", response.message)
                    }else{
                        Log.d("12345", "responseStr  not succesfull" + response.body!!.string())
                        viewState.onError(1)
                    }
                }
            }, userModel = userModel)
        }
    }

    fun registration(userModel : UserModelForReg, context : Context) {
        viewState.startAction()
        // ползем в провайдер
        GlobalScope.launch {
            AuthProvider.registrationRequest(callback = object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    viewState.onError(1)
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val responseStr = response.body!!.string()
                        val myJson = JSONObject(responseStr)
                        val arr = myJson.getJSONObject("data")
                        val tokenType = arr.getString("token_type")
                        if(tokenType == "Bearer"){
                            val accessToken = arr.getString("access_token")
                            saveToken(accessToken, context)
                            viewState.onSuccess()
                        }
                        Log.d("12345", responseStr)
                    }else{
                        Log.d("12345", "responseStr  not succesfull" + response.body!!.string())
                        viewState.onError(1)
                    }
                }
            }, userModel = userModel)
        }

    }

    fun saveToken(token : String, context : Context){
        mSettings = context.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
        editor = mSettings.edit()
        editor.putString(Constants.USER_ACCESS_TOKEN, token)
        editor.apply()
    }

    fun go (controller: NavController) {
//        controller.navigate(R.id.action_loginFragment_to_main3)
    }
    fun onSuccesRegistrationNavigate(controller: NavController){
//        controller.navigate(R.id.action_registrationFragment_to_postRegistrationFragment)
    }
    fun goRegistration (controller: NavController) {
//        controller.navigate(R.id.action_loginFragment_to_registrationFragment)
    }
    fun getLoginResult() {

    }

}