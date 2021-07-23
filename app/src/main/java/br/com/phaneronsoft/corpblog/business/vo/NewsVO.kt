package br.com.phaneronsoft.corpblog.business.vo

import java.io.Serializable

data class NewsVO(
    val user: UserVO,
    val message: MessageVO,
) : Serializable