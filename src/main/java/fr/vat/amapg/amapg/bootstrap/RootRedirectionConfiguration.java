package fr.vat.amapg.amapg.bootstrap;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static fr.vat.amapg.amapg.bootstrap.constant.Route.ROOT;
import static fr.vat.amapg.amapg.bootstrap.constant.Route.SHOWCASE;

@Configuration
public class RootRedirectionConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController(ROOT, SHOWCASE);
    }

}
