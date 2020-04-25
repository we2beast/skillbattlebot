package com.greenatom.skillbattle.bot.services

import com.greenatom.skillbattle.bot.configuration.KeyboardConfiguration
import com.greenatom.skillbattle.bot.utils.MessageUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import java.util.ArrayList

@Service
class BattleServiceImpl(
        val messageUtil: MessageUtil
) {

    fun battleMethod(message: Message): SendMessage {
        val response = messageUtil.initMessageWithChatId(message)
        response.text = "Это баттл. Капитулироваться можно ниже :)"

        return battleKeyboardMethod(response)
    }

    private fun battleKeyboardMethod(response: SendMessage): SendMessage {
        val keyboardMarkup = ReplyKeyboardMarkup()
        val keyboard: MutableList<KeyboardRow> = ArrayList()
        val row = KeyboardRow()
        keyboard.add(row)
        row.add(KeyboardConfiguration.CAPITULATION)
        keyboardMarkup.keyboard = keyboard
        response.replyMarkup = keyboardMarkup

        return response
    }

}
