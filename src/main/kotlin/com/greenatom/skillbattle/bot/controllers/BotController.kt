package com.greenatom.skillbattle.bot.controllers

import com.greenatom.skillbattle.bot.configuration.KeyboardConfiguration.Companion.BATTLE
import com.greenatom.skillbattle.bot.configuration.KeyboardConfiguration.Companion.CAPITULATION
import com.greenatom.skillbattle.bot.configuration.KeyboardConfiguration.Companion.DESCRIPTION
import com.greenatom.skillbattle.bot.configuration.KeyboardConfiguration.Companion.SETTINGS
import com.greenatom.skillbattle.bot.configuration.KeyboardConfiguration.Companion.STATISTICS
import com.greenatom.skillbattle.bot.services.BattleServiceImpl
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

    @Autowired
    lateinit var battleServiceImpl: BattleServiceImpl

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

            when (update.message.text) {
                "/start" -> {
                    sendMessage(botServiceImpl.startMethod(update.message))
                    return
                }
                "/markup" -> {
                    sendMessage(botServiceImpl.markUpMethod(update.message))
                    return
                }
                BATTLE -> {
                    sendMessage(battleServiceImpl.battleMethod(update.message))
                    return
                }
                DESCRIPTION -> {
                    TODO("Battle service is here")
                }
                STATISTICS -> {
                    TODO("Battle service is here")
                }
                SETTINGS -> {
                    TODO("Battle service is here")
                }
                CAPITULATION -> {
                    sendMessage(botServiceImpl.markUpMethod(update.message))
                    return
                }
                else -> {
                    val message = prepareMessage(update.message.chatId, "Такой команды нет")
                    sendMessage(message)
                }
            }

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
