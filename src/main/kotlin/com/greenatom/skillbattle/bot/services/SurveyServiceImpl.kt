package com.greenatom.skillbattle.bot.services

import com.greenatom.skillbattle.bot.repositories.SurveyRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SurveyServiceImpl {

    @Autowired
    lateinit var surveyRepository: SurveyRepository

    private val log = LoggerFactory.getLogger(SurveyServiceImpl::class.java)

}
