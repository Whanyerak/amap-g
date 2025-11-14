package fr.vat.amapg.amapg.security.register.controller;

import fr.vat.amapg.amapg.security.register.RegisterUser;
import fr.vat.amapg.amapg.security.register.entity.User;
import fr.vat.amapg.amapg.security.register.entity.UserToRegisterDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterUser registerUser;

    @GetMapping("/register")
    public String accessToRegisterForm(Model model) {
        model.addAttribute("userToRegister", new UserToRegisterDto());
        return "security/register";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute @Valid UserToRegisterDto userToRegisterDto) {
        log.info("Demande d'inscription reçue pour {}", userToRegisterDto.toString());

        var user = new User(
                userToRegisterDto.getFirstName(),
                userToRegisterDto.getLastName(),
                userToRegisterDto.getAddress(),
                userToRegisterDto.getPostalCode(),
                userToRegisterDto.getCity(),
                userToRegisterDto.getPhoneNumber(),
                userToRegisterDto.getEmail()
        );

        registerUser.execute(user);

        return "redirect:/common/homepage";
    }

}
