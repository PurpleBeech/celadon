/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package nk.unc.celadon.web;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.messageresolver.IMessageResolver;
import org.thymeleaf.messageresolver.StandardMessageResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

/**
 *
 * @author neil
 */
@ApplicationScoped
public class ThymeleafConfiguration {

    /**
     * The logger used by this ThymeleafConfiguration.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ThymeleafConfiguration.class);

    /**
     * Default time to live, TTL, of the cache.
     */
    private static final long TTL_DEFAULT = 3600000L;

    private JakartaServletWebApplication application;

    private ServletContext context;

    private WebApplicationTemplateResolver templateResolver;
    
    private TemplateEngine templateEngine;

    /**
     * @return the application
     */
    @Produces
    public JakartaServletWebApplication getApplication() {
        LOGGER.info("getApplication application is NULL={}", application == null);
        return application;
    }
    
    @Produces
    public TemplateEngine getTemplateEngine() {
        LOGGER.info("getApplication templateEngine is NULL={}", templateEngine == null);
        return this.templateEngine;
    }

    @PostConstruct
    public void configure() {
        LOGGER.info("configure context is NULL={}", context == null);
        configureApplication();
        configureTemplateResolver();
        configureTemplateEngine();
    }

    private void configureApplication() {
        LOGGER.info("configureApplication context is NULL={}", context == null);
        application = JakartaServletWebApplication.buildApplication(context);
    }

    /**
     * @param context the context to set
     */
    @Inject
    public void setContext(ServletContext context) {
        LOGGER.info("setContext");
        this.context = context;
    }

    private void configureTemplateEngine() {
        LOGGER.info("configureTemplateEngine templateResolver is NULL={}", templateResolver == null);
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        
        StandardMessageResolver messageResolver = new StandardMessageResolver();
        messageResolver.addDefaultMessage("celadon.title", "Celadon");
        messageResolver.addDefaultMessage("msg.today", "Today is {0}.");
        templateEngine.setMessageResolver(messageResolver);
    }

    private void configureTemplateResolver() {
        LOGGER.info("configureTemplateResolver application is NULL={}", application == null);
        templateResolver = new WebApplicationTemplateResolver(application);

        // HTML is the default mode, but we will set it anyway for better understanding of code
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // This will convert "home" to "/WEB-INF/templates/home.html"
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        // Set template cache TTL to 1 hour. If not set, entries would live in cache until expelled by LRU
        templateResolver.setCacheTTLMs(TTL_DEFAULT);

        // Cache is set to true by default. Set to false if you want templates to
        // be automatically updated when modified.
        templateResolver.setCacheable(true);
    }

}
