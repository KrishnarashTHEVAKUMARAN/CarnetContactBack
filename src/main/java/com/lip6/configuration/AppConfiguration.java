package com.lip6.configuration;

import com.lip6.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = {"classpath:applicationContext.xml"})
@ComponentScan(basePackages = {"com.lip6.*"})
public class AppConfiguration {

    @Autowired
    private ApplicationContext context;

    public Contact getFirstContactFromBean() {
        return (Contact) context.getBean("firstContactFromBean");
    }
}
