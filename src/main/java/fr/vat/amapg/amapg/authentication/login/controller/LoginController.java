package fr.vat.amapg.amapg.authentication.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/auth")
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login-error")
    public String failedLogin(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

}
