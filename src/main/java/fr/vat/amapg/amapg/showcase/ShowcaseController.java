package fr.vat.amapg.amapg.showcase;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowcaseController {

    @GetMapping
    public String welcome() {
        return "showcase/index";
    }

}
