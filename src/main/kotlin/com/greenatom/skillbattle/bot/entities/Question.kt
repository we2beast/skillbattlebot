package com.greenatom.skillbattle.bot.entities

import javax.persistence.*

@Entity
@Table(name = "questions")
data class Question(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "question_id")
        var id: Int? = null,

        @Column(name = "title", columnDefinition="TEXT")
        var title: String? = null,

        @OneToMany(cascade = [CascadeType.MERGE])
        @JoinTable(name = "question_answer", joinColumns = [JoinColumn(name = "question_id")], inverseJoinColumns = [JoinColumn(name = "answer_id")])
        var answers: Set<Answer> = setOf()
)
