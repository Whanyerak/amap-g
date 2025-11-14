package fr.vat.amapg.amapg.welcome;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/index")
    public String welcome() {
        return "index";
    }

}
