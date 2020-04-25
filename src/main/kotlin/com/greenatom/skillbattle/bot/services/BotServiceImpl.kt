package com.greenatom.skillbattle.bot.services

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message

@Service
class BotServiceImpl {

    fun startMethod(message: Message): SendMessage {
        val response = SendMessage()
        val chatId = message.chatId
        response.setChatId(chatId)
        response.text = "Hello Dear\nThis is description"
        return response
    }

}
