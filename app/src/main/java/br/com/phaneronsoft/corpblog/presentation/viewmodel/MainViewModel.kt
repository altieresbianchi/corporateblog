package br.com.phaneronsoft.corpblog.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.phaneronsoft.corpblog.business.repository.PostRepositoryContract
import br.com.phaneronsoft.corpblog.business.repository.UserRepositoryContract
import br.com.phaneronsoft.corpblog.business.vo.PostVO
import br.com.phaneronsoft.corpblog.business.vo.UserVO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainViewModel(
    private val coroutineContext: CoroutineContext,
    private val userRepository: UserRepositoryContract,
    private val postRepository: PostRepositoryContract
) : ViewModel() {
    private val coroutineScope = CoroutineScope(coroutineContext)

    val postList = MutableLiveData<MutableList<PostVO>>()
    val isLoading = MutableLiveData<Boolean>()
    val successDelete = MutableLiveData<Boolean>()
    val isLogout = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    var user: UserVO? = null

    fun getUser() {
        user = userRepository.getUser()
    }

    fun fetchPostList() {
        coroutineScope.launch {
            try {
                isLoading.postValue(true)
                postList.postValue(postRepository.fetchList(user!!))

            } catch (e: Exception) {
                Log.e(javaClass.simpleName, e.message, e)

                error.postValue(e.message)
            }
            isLoading.postValue(false)
        }
    }

    fun delete(postId: String) {
        coroutineScope.launch {
            try {
                isLoading.postValue(true)
                successDelete.postValue(postRepository.delete(user!!.id, postId))

            } catch (e: Exception) {
                Log.e(javaClass.simpleName, e.message, e)

                error.postValue(e.message)
            }
            isLoading.postValue(false)
        }
    }

    fun logout() {
        coroutineScope.launch {
            try {
                isLogout.postValue(userRepository.logout())
            } catch (e: Exception) {
                error.postValue(e.message)
            }
        }
    }
}