package com.greenatom.skillbattle.bot.entities

import javax.persistence.*

@Entity
@Table(name = "games")
data class Games(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Int? = null,

        @ManyToOne(cascade = [CascadeType.MERGE])
        @JoinColumn(name = "enemy_first")
        val enemyFirst: Gamer,

        @ManyToOne(cascade = [CascadeType.MERGE])
        @JoinColumn(name = "enemy_second")
        val enemySecond: Gamer,

        @OneToOne(cascade = [CascadeType.MERGE])
        @MapsId("battleId")
        val battle: Battle
)
