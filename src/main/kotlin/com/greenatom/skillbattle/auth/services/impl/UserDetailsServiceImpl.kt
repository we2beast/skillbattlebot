package com.greenatom.skillbattle.auth.services.impl

import com.greenatom.skillbattle.auth.entities.Role
import com.greenatom.skillbattle.auth.entities.UserEntity
import com.greenatom.skillbattle.auth.services.UserService
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class UserDetailsServiceImpl(
    val userService: UserService
) : UserDetailsService {

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(userName: String): UserDetails? {
        val user: UserEntity? = userService.findUserByUserName(userName)
        val authorities = user?.roles?.let { getUserAuthority(it) }
        return authorities?.let { buildUserForAuthentication(user, it) }
    }

    private fun getUserAuthority(userRoles: Set<Role>): List<GrantedAuthority> {
        val roles: MutableSet<GrantedAuthority> = HashSet()
        for (role in userRoles) {
            roles.add(SimpleGrantedAuthority(role.role))
        }
        return ArrayList(roles)
    }

    private fun buildUserForAuthentication(user: UserEntity, authorities: List<GrantedAuthority>): UserDetails {
        return User(user.email, user.password, user.active ?: false, true, true, true, authorities)
    }

}
