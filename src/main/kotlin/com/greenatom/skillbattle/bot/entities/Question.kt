package com.greenatom.skillbattle.bot.entities

import javax.persistence.*

@Entity
@Table(name = "questions")
data class Question(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Int? = null,

        @Column(name = "title", columnDefinition = "TEXT")
        var title: String? = null,

        @Column(name = "category", columnDefinition = "TEXT")
        var category: Category = Category.NONE,

        @OneToMany(mappedBy = "questions", fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
        var answers: Set<Answer> = setOf(),

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "battles_id")
        var battles: Battle
)

enum class Category {
    NONE, JUNIOR, MIDDLE, SENIOR
}
