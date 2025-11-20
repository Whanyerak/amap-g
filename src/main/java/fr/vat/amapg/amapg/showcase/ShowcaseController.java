package fr.vat.amapg.amapg.showcase;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static fr.vat.amapg.amapg.bootstrap.constant.Route.SHOWCASE;

@Controller
@RequestMapping(SHOWCASE)
public class ShowcaseController {

    @GetMapping
    public String welcome() {
        return "showcase/index";
    }

}
