package com.greenatom.skillbattle.bot.services

import com.greenatom.skillbattle.bot.entities.Battle
import com.greenatom.skillbattle.bot.entities.Gamer
import com.greenatom.skillbattle.bot.repositories.GamerRepository
import com.greenatom.skillbattle.bot.repositories.SurveyRepository
import com.greenatom.skillbattle.core.exception.EntityNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GamerServiceImpl {

    @Autowired
    lateinit var gamerRepository: GamerRepository

    @Autowired
    lateinit var surveyRepository: SurveyRepository

    private val log = LoggerFactory.getLogger(GamerServiceImpl::class.java)

    fun existUserOrSave(chatId: String, eventId: String) {
        val survey = surveyRepository.findFirstByTitleAndStartedIsTrue(eventId) ?: throw EntityNotFoundException("Survey with $eventId not found")

        val foundChatId = gamerRepository.findFirstByChatIdAndBattles(chatId, survey)
        if (foundChatId != null) {
            println("Exists")
        } else {
            val id = gamerRepository.save(Gamer(chatId = chatId, battles = setOf(survey))).id ?: throw IllegalArgumentException("Bad id returned.")

            log.debug("Created entity $id")
        }
    }

}
