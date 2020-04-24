package com.greenatom.skillbattle.auth.entities

import org.hibernate.validator.constraints.Length
import javax.persistence.*
import javax.validation.constraints.Email

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    var id: Int? = null,

    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
    var email: String? = null,

    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    var password: String? = null,

    @Column(name = "name")
    var name: String? = null,

    @Column(name = "last_name")
    var lastName: String? = null,

    @Column(name = "active")
    var active: Boolean? = null,

    @ManyToMany(cascade = [CascadeType.MERGE])
    @JoinTable(name = "user_role", joinColumns = [JoinColumn(name = "user_id")], inverseJoinColumns = [JoinColumn(name = "role_id")])
    var roles: Set<Role> = setOf()
)
