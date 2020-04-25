package com.greenatom.skillbattle.bot.entities

import javax.persistence.*

@Entity
@Table(name = "gamers")
data class Gamer(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "gamer_id")
        var id: Int? = null,

        @Column(name = "chat_id")
        var chatId: String? = null,

        @ManyToMany(cascade = [CascadeType.MERGE])
        @JoinTable(name = "gamer_battle", joinColumns = [JoinColumn(name = "gamer_id")], inverseJoinColumns = [JoinColumn(name = "battle_id")])
        var battles: Set<Battle> = setOf(),

        @ManyToMany(cascade = [CascadeType.MERGE])
        @JoinTable(name = "gamer_answer", joinColumns = [JoinColumn(name = "gamer_id")], inverseJoinColumns = [JoinColumn(name = "answer_id")])
        var answers: Set<Answer> = setOf()
)
