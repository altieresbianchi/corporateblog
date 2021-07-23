package br.com.phaneronsoft.corpblog.business.repository

import br.com.phaneronsoft.corpblog.business.vo.UserVO

interface UserRepositoryContract {
    fun signUp(user: UserVO): Boolean

    fun login(email: String, password: String): Boolean

    fun getUser(): UserVO?

    fun logout(): Boolean
}