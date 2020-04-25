package com.greenatom.skillbattle.bot.entities

import javax.persistence.*

@Entity
@Table(name = "answers")
data class Answer(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Int? = null,

        @Column(name = "label", columnDefinition="TEXT")
        var label: String? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @MapsId("question_id")
        var questions: Question
)
