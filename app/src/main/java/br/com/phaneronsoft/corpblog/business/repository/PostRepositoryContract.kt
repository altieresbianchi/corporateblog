package br.com.phaneronsoft.corpblog.business.repository

import br.com.phaneronsoft.corpblog.business.vo.PostVO
import br.com.phaneronsoft.corpblog.business.vo.UserVO

interface PostRepositoryContract {
    fun fetchList(user: UserVO): MutableList<PostVO>

    fun create(postVO: PostVO): PostVO

    fun update(postVO: PostVO): Boolean

    fun delete(userId: String, postId: String): Boolean
}