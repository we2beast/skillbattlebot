package com.greenatom.skillbattle.bot.controllers

import com.greenatom.skillbattle.bot.services.BotServiceImpl
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMembersCount
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import javax.annotation.PostConstruct

@Component
class Bot : TelegramLongPollingBot() {

    private val log = LoggerFactory.getLogger(Bot::class.java)

    @Autowired
    lateinit var botServiceImpl: BotServiceImpl

    @Value("\${bot.token}")
    private val token: String? = null

    @Value("\${bot.username}")
    private val username: String? = null

    override fun getBotToken(): String {
        return token!!
    }

    override fun getBotUsername(): String {
        return username!!
    }

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage()) {
            val chat = update.message.chat
            val chatMembersCount = GetChatMembersCount()
            chatMembersCount.chatId = update.message.chatId.toString()

            if (execute(chatMembersCount) != 2 || chat.isChannelChat || chat.isSuperGroupChat) {
                sendMessage(prepareMessage(update.message.chatId, "Это групповой чат. Меня не взломать :)"))
                return
            }

            val textMessage = update.message.text

            if (textMessage == "/start") {
                sendMessage(botServiceImpl.startMethod(update.message))
                return
            }

            if (textMessage == "/markup") {
                sendMessage(botServiceImpl.markUpMethod(update.message))
                return
            }

            if (textMessage == "⚔️ Битва") {
                println("Ti pisun")
            }

//            val message: Message = update.message
//            val response = SendMessage()
//            val chatId = message.chatId
//            response.setChatId(chatId)
//            val text = message.text
//            response.text = text

        }
    }

    private fun sendMessage(message: SendMessage) {
        val textMessage = message.text
        val chatId = message.chatId

        try {
            execute(message)
            log.info("Sent message \"{}\" to {}", textMessage, chatId) // TODO: Убрать при показе
        } catch (e: TelegramApiException) {
            log.error("Failed to send message \"{}\" to {} due to error: {}", textMessage, chatId, e.message)
        }
    }

    private fun prepareMessage(chatId: Long, message: String): SendMessage {
        val response = SendMessage()
        response.chatId = chatId.toString()
        response.text = message
        return response
    }

    @PostConstruct
    fun start() {
        log.info("username: {}, token: {}", username, token)
    }

}
