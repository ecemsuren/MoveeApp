package com.example.moveeapp.ui.modules.login.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.moveeapp.R
import com.example.moveeapp.application.MoveeApplication
import com.example.moveeapp.data.components.network.base.ModelConstants
import com.example.moveeapp.data.components.network.repository.tmdb.api.TMDBApi
import com.example.moveeapp.data.components.network.repository.tmdb.model.CreateSessionResponse
import com.example.moveeapp.data.components.network.repository.tmdb.model.GuestResponse
import com.example.moveeapp.data.components.network.repository.tmdb.model.RequestTokenResponse
import com.example.moveeapp.data.components.network.repository.tmdb.model.SessionWithLoginResponse
import com.example.moveeapp.data.model.tmdb.CreateSession
import com.example.moveeapp.data.model.tmdb.UserInfo
import com.example.moveeapp.ui.modules.login.base.LoginBaseViewModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ecem Suren on 24.10.2020.
 */
class LoginViewModel: LoginBaseViewModel() {

    val userName: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> =MutableLiveData()

    val guestResponse : MutableLiveData<GuestResponse> = MutableLiveData()
    val errorMessage : MutableLiveData<String> = MutableLiveData()
    val createTokenResponse: MutableLiveData<RequestTokenResponse> = MutableLiveData()
    val sessionWithLoginResponse: MutableLiveData<SessionWithLoginResponse> = MutableLiveData()
    val createSessionResponse: MutableLiveData<CreateSessionResponse> = MutableLiveData()

    companion object{
        lateinit var requestToken: String
    }

    private var guestCallback = object : Callback<GuestResponse> {
        override fun onResponse(
            call: Call<GuestResponse>,
            response: Response<GuestResponse>
        ) {
            if (response.isSuccessful) {
                guestResponse.value = response.body()
            }else{
                errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
            }
        }

        override fun onFailure(call: Call<GuestResponse>, t: Throwable) {
            errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
        }
    }

    private var loginRequestTokenCallback = object : Callback<RequestTokenResponse> {
        override fun onResponse(
            call: Call<RequestTokenResponse>,
            response: Response<RequestTokenResponse>
        ) {
            if (response.isSuccessful) {
                createTokenResponse.value = response.body()
            }else{
                errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
            }
        }

        override fun onFailure(call: Call<RequestTokenResponse>, t: Throwable) {
            errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
        }
    }

    private var sessionWithLoginCallback = object : Callback<SessionWithLoginResponse> {
        override fun onResponse(
            call: Call<SessionWithLoginResponse>,
            response: Response<SessionWithLoginResponse>
        ) {
            if (response.isSuccessful) {
                sessionWithLoginResponse.value = response.body()
            } else {
                val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                val errorMsg = jsonObj.getString(ModelConstants.STATUS_MESSAGE_STRING)
                errorMessage.value = errorMsg
                print(errorMessage)
            }
        }

        override fun onFailure(call: Call<SessionWithLoginResponse>, t: Throwable) {
            errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
        }
    }
    private var createSessionCallback = object : Callback<CreateSessionResponse> {
        override fun onResponse(
            call: Call<CreateSessionResponse>,
            response: Response<CreateSessionResponse>
        ) {
            if (response.isSuccessful) {
                createSessionResponse.value = response.body()
            }else{
                errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)

            }
        }

        override fun onFailure(call: Call<CreateSessionResponse>, t: Throwable) {
            errorMessage.value = MoveeApplication.appContext.getString(R.string.failed)
        }
    }

    fun getGuest() {
        TMDBApi.getGuest(guestCallback)
    }

    fun getCreateToken(){
        TMDBApi.getCreateToken(loginRequestTokenCallback)
    }
    fun getSessionWithLogin(userInfo: UserInfo) {
        TMDBApi.getSessionWithLogin(userInfo, sessionWithLoginCallback)
    }

    fun createSession(request_token: CreateSession) {
        TMDBApi.getCreateSession(request_token,createSessionCallback)
    }
}