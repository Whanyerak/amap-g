package fr.vat.amapg.amapg.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static fr.vat.amapg.amapg.bootstrap.constant.Route.LOGIN;
import static fr.vat.amapg.amapg.bootstrap.constant.Route.LOGIN_ERROR;
import static fr.vat.amapg.amapg.bootstrap.constant.Route.LOGOUT;
import static fr.vat.amapg.amapg.bootstrap.constant.Template.LOGIN_PAGE;
import static fr.vat.amapg.amapg.bootstrap.constant.Template.LOGOUT_PAGE;

@Controller
@RequestMapping
public class AuthenticationController {

    @GetMapping(LOGIN)
    public String login() {
        return LOGIN_PAGE;
    }

    @GetMapping(LOGIN_ERROR)
    public String failedLogin(Model model) {
        model.addAttribute("loginError", true);

        return LOGIN_PAGE;
    }

    @GetMapping(LOGOUT)
    public String logout() {
        return LOGOUT_PAGE;
    }

}
