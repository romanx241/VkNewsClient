package ru.netology.vknewsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult
import ru.netology.vknewsclient.ui.theme.AuthState

class MainViewModel: ViewModel() {

    private val _authState = MutableLiveData<AuthState>(AuthState.Initial)
    val authState : LiveData<AuthState> = _authState

    init {
        _authState.value = if(VK.isLoggedIn()) AuthState.Authorized else AuthState.NotAuthorized
    }

    fun performAythResult(result: VKAuthenticationResult){
        if(result is VKAuthenticationResult.Success){
            _authState.value = AuthState.Authorized
        } else {
            _authState.value = AuthState.NotAuthorized

        }
    }
}