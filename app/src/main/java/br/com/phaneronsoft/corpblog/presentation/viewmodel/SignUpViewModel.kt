package br.com.phaneronsoft.corpblog.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.phaneronsoft.corpblog.business.repository.UserRepositoryContract
import br.com.phaneronsoft.corpblog.business.vo.UserVO
import br.com.phaneronsoft.corpblog.utils.isValidEmail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SignUpViewModel(
    private val coroutineContext: CoroutineContext,
    private val userRepository: UserRepositoryContract
) : ViewModel() {
    private val coroutineScope = CoroutineScope(coroutineContext)

    val isSignUpSuccess = MutableLiveData<Boolean>()
    val isValidForm = MutableLiveData<Boolean>()
    val errorName = MutableLiveData<Boolean>()
    val errorEmail = MutableLiveData<Boolean>()
    val errorPassword = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun checkValidFormData(user: UserVO) {
        errorName.value = user.name.isEmpty() || user.name.length < 3
        errorEmail.value = !user.email.isValidEmail()
        errorPassword.value = user.password.isEmpty() || user.password.length < 3

        isValidForm.value = (!errorName.value!! && !errorEmail.value!! && !errorPassword.value!!)
    }

    fun signUp(user: UserVO) {
        coroutineScope.launch {
            try {
                isSignUpSuccess.postValue(userRepository.signUp(user))

            } catch (e: Exception) {
                error.postValue(e.message)
            }
        }
    }
}