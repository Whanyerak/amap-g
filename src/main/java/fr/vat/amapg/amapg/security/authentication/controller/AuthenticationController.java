package fr.vat.amapg.amapg.security.authentication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
public class AuthenticationController {

    @GetMapping("/login")
    public String login() {
        return "security/login";
    }

    @GetMapping("/login-error") // TODO investigate to use ControllerAdvice
    public String failedLogin(Model model) {
        model.addAttribute("loginError", true);
        return "security/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "security/logout";
    }

}
