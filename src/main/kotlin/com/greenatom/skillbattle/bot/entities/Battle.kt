package com.greenatom.skillbattle.bot.entities

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "battles")
data class Battle(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "battle_id")
        var id: Int? = null,

        @Column(name = "title")
        var title: String? = null,

        @Column(name = "description", columnDefinition="TEXT")
        var description: String? = null,

        @Column(name = "closed")
        var closed: Boolean = false,

        @Column(name = "start_date")
        var startDate: LocalDateTime? = null,

        @Column(name = "end_date")
        var endDate: LocalDateTime? = null,

        @Column(name = "speed")
        var speed: Int? = null,

        @Column(name = "started")
        var started: Boolean = false
)
