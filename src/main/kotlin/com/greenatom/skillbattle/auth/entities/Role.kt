package com.greenatom.skillbattle.auth.entities

import javax.persistence.*

@Entity
@Table(name = "roles")
data class Role(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "role_id")
        var id: Int? = null,

        @Column(name = "role")
        var role: String? = null
)
