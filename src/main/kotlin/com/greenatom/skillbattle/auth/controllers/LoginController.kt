package com.greenatom.skillbattle.auth.controllers

import com.greenatom.skillbattle.auth.entities.UserEntity
import com.greenatom.skillbattle.auth.services.UserService
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView
import javax.validation.Valid

@Controller
class LoginController(
        val userService: UserService
) {

    @RequestMapping(value = ["/"], method = [RequestMethod.GET])
    fun login(): ModelAndView {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "login"

        return modelAndView
    }

    @RequestMapping(value = ["/registration"], method = [RequestMethod.GET])
    fun registration(): ModelAndView {
        val modelAndView = ModelAndView()
        val user = UserEntity()
        modelAndView.addObject("user", user)
        modelAndView.viewName = "registration"

        return modelAndView
    }

    @RequestMapping(value = ["/registration"], method = [RequestMethod.POST])
    fun createNewUser(user: @Valid UserEntity, bindingResult: BindingResult): ModelAndView {
        val modelAndView = ModelAndView()
        val userExists: UserEntity? = userService.findUserByEmail(user.email!!) // Опасно делать
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided")
        }
        if (bindingResult.hasErrors()) {
            modelAndView.viewName = "registration"
        } else {
            userService.saveUser(user)
            modelAndView.addObject("successMessage", "User has been registered successfully")
            modelAndView.addObject("user", UserEntity())
            modelAndView.viewName = "registration"
        }

        return modelAndView
    }

    @RequestMapping(value = ["/admin/home"], method = [RequestMethod.GET])
    fun home(): ModelAndView {
        val modelAndView = ModelAndView()
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        val user: UserEntity? = userService.findUserByEmail(auth.name)
        modelAndView.addObject("userName", "Welcome " + user?.email + "/" + user?.name + " " + user?.lastName + " (" + user?.email + ")")
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role")
        modelAndView.viewName = "admin/home"
        return modelAndView
    }
}
