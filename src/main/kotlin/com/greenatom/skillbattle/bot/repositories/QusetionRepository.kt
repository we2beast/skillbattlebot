package com.greenatom.skillbattle.bot.repositories

import com.greenatom.skillbattle.bot.entities.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository : JpaRepository<Question, Int> {
}
