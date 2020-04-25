package com.greenatom.skillbattle.bot.repositories

import com.greenatom.skillbattle.bot.entities.Battle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SurveyRepository : JpaRepository<Battle, Long> {
    fun findFirstByTitleAndStartedIsTrue(title: String): Battle?
}
