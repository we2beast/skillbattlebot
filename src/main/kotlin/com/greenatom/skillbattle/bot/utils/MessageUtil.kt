package com.greenatom.skillbattle.bot.utils

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message

@Component
class MessageUtil {

    fun initMessageWithChatId(message: Message): SendMessage {
        val response = SendMessage()
        val chatId = message.chatId
        response.setChatId(chatId)

        return response
    }

}
