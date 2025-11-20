package fr.vat.amapg.amapg.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class AmapGController {

    @GetMapping("/homepage")
    public String homepage() {
        return "common/homepage";
    }

}
