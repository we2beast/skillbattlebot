package com.greenatom.skillbattle.bot.repositories

import com.greenatom.skillbattle.bot.entities.Battle
import com.greenatom.skillbattle.bot.entities.Gamer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GamerRepository : JpaRepository<Gamer, Int> {
    fun findFirstByChatIdAndBattles(chatId: String, battle: Battle): Gamer?
}
