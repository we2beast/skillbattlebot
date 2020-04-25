package com.greenatom.skillbattle.bot.services

import com.greenatom.skillbattle.bot.configuration.KeyboardConfiguration.Companion.BATTLE
import com.greenatom.skillbattle.bot.configuration.KeyboardConfiguration.Companion.DESCRIPTION
import com.greenatom.skillbattle.bot.configuration.KeyboardConfiguration.Companion.SETTINGS
import com.greenatom.skillbattle.bot.configuration.KeyboardConfiguration.Companion.STATISTICS
import com.greenatom.skillbattle.bot.repositories.SurveyRepository
import com.greenatom.skillbattle.bot.utils.MessageUtil
import com.greenatom.skillbattle.core.exception.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import java.util.ArrayList

@Service
class BotServiceImpl(
        val messageUtil: MessageUtil
) {

    @Autowired
    lateinit var surveyRepository: SurveyRepository

    fun startMethod(message: Message, eventId: String): SendMessage {
        val survey = surveyRepository.findFirstByTitleAndStartedIsTrue(eventId) ?: throw EntityNotFoundException("Survey with $eventId not found")

        val response = messageUtil.initMessageWithChatId(message)
        response.text = survey.description

        return keyboardMethod(response)
    }

    fun markUpMethod(message: Message): SendMessage {
        val response = messageUtil.initMessageWithChatId(message)
        response.text = "Вызов клавиатуры"

        return keyboardMethod(response)
    }

    private fun keyboardMethod(response: SendMessage): SendMessage {
        val keyboardMarkup = ReplyKeyboardMarkup()
        val keyboard: MutableList<KeyboardRow> = ArrayList()
        var row = KeyboardRow()
        row.add(BATTLE)
        keyboard.add(row)
        row = KeyboardRow()
        row.add(DESCRIPTION)
        keyboard.add(row)
        row = KeyboardRow()
        row.add(STATISTICS)
        row.add(SETTINGS)
        keyboard.add(row)
        keyboardMarkup.keyboard = keyboard
        response.replyMarkup = keyboardMarkup

        return response
    }

}
