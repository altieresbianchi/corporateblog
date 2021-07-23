package br.com.phaneronsoft.corpblog.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.phaneronsoft.corpblog.business.repository.UserRepositoryContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SplashViewModel(
    private val coroutineContext: CoroutineContext,
    private val userRepository: UserRepositoryContract
) : ViewModel() {
    private val coroutineScope = CoroutineScope(coroutineContext)

    val isAuthenticated = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun checkAuthentication() {
        coroutineScope.launch {
            try {
                delay(1500)

                isAuthenticated.postValue(userRepository.getUser() != null)
            } catch (e: Exception) {
                error.postValue(e.message)
            }
        }
    }
}