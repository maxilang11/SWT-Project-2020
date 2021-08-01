package de.hsrm.mi.swtpro.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login")
            .setViewName("forward:/index.html");
    registry.addViewController("/startseite")
            .setViewName("forward:/index.html");
    registry.addViewController("/kategorien/**")
            .setViewName("forward:/index.html");
    registry.addViewController("/warenkorb")
            .setViewName("forward:/index.html");
    registry.addViewController("/bookmarks")
            .setViewName("forward:/index.html");  
    registry.addViewController("/")
            .setViewName("forward:/index.html");  
    registry.addViewController("/register")
            .setViewName("forward:/index.html");  
    registry.addViewController("/bestellbestaetigung")
            .setViewName("forward:/index.html");  
    registry.addViewController("/profil")
            .setViewName("forward:/index.html");  
    registry.addViewController("/lagerist/bestellungen")
            .setViewName("forward:/index.html");  
    registry.addViewController("/lagerist/bestelldetails/**")
            .setViewName("forward:/index.html");  
    registry.addViewController("/admin/bar")
            .setViewName("forward:/index.html");  
    registry.addViewController("/admin/startseite")
            .setViewName("forward:/index.html");  
    registry.addViewController("/:pathMatch(.*)*")
            .setViewName("forward:/index.html");  
    registry.addViewController("/bestelluebersicht")
            .setViewName("forward:/index.html");  
    registry.addViewController("/admin/produkte")
            .setViewName("forward:/index.html");  
    registry.addViewController("/lagerist/produktuebersicht")
            .setViewName("forward:/index.html");  
    registry.addViewController("/lagerist/produkte")
            .setViewName("forward:/index.html");  
  }
}
