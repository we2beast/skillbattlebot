package com.greenatom.skillbattle.auth.services.impl

import com.greenatom.skillbattle.auth.entities.Role
import com.greenatom.skillbattle.auth.entities.UserEntity
import com.greenatom.skillbattle.auth.repositories.RoleRepository
import com.greenatom.skillbattle.auth.repositories.UserRepository
import com.greenatom.skillbattle.auth.services.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(val bCryptPasswordEncoder: BCryptPasswordEncoder) : UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var roleRepository: RoleRepository

    private val log = LoggerFactory.getLogger(UserServiceImpl::class.java)

    override fun findUserByEmail(email: String): UserEntity? {
        return userRepository.findByEmail(email)
    }

    override fun findUserByUserName(userName: String): UserEntity? {
        return userRepository.findByEmail(userName)
    }

    override fun saveUser(user: UserEntity): UserEntity {
        user.password = bCryptPasswordEncoder.encode(user.password)
        user.active = true
        val userRole: Role = roleRepository.findByRole("ADMIN")
        user.roles = setOf(userRole)

        log.debug("Created entity ${user.id}")
        return userRepository.save(user)
    }

}
