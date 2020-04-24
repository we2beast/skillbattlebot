package com.greenatom.skillbattle.auth.repositories

import com.greenatom.skillbattle.auth.entities.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Int> {
    fun findByRole(role: String): Role
}
