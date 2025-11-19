package fr.vat.amapg.amapg.bootstrap;

import fr.vat.amapg.amapg.bootstrap.constant.Route;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributes {

    @ModelAttribute
    public void addGlobalAttributes(Model model) {
        model.addAttribute("Route", Route.class);
    }

}