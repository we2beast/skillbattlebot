package com.greenatom.skillbattle.bot.services

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import java.util.ArrayList

@Service
class BotServiceImpl {

    fun startMethod(message: Message): SendMessage {
        val response = initMessageWithChatId(message)
        response.text = "Привет!\nЭто бот\nДля вызова меню отправьте /markup"

        return keyboardMethod(response)
    }

    fun markUpMethod(message: Message): SendMessage {
        val response = initMessageWithChatId(message)
        response.text = "Вызов клавиатуры"

        return keyboardMethod(response)
    }


    private fun keyboardMethod(response: SendMessage): SendMessage {
        val keyboardMarkup = ReplyKeyboardMarkup()
        val keyboard: MutableList<KeyboardRow> = ArrayList()
        var row = KeyboardRow()
        row.add("Row 1 Button 1")
        row.add("Row 1 Button 2")
        row.add("Row 1 Button 3")
        keyboard.add(row)
        row = KeyboardRow()
        row.add("Row 2 Button 1")
        row.add("Row 2 Button 2")
        row.add("Row 2 Button 3")
        keyboard.add(row)
        keyboardMarkup.keyboard = keyboard
        response.replyMarkup = keyboardMarkup

        return response
    }

    private fun initMessageWithChatId(message: Message): SendMessage {
        val response = SendMessage()
        val chatId = message.chatId
        response.setChatId(chatId)

        return response
    }

}
