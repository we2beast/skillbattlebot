package com.greenatom.skillbattle.auth.services

import com.greenatom.skillbattle.auth.entities.UserEntity

interface UserService {

    fun findUserByEmail(email: String): UserEntity?

    fun findUserByUserName(userName: String): UserEntity?

    fun saveUser(user: UserEntity): UserEntity

}
