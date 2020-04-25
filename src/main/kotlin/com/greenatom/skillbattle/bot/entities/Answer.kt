package com.greenatom.skillbattle.bot.entities

import javax.persistence.*

@Entity
@Table(name = "answers")
data class Answer(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "answer_id")
        var id: Int? = null,

        @Column(name = "label", columnDefinition="TEXT")
        var label: String? = null
)
