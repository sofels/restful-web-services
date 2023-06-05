package com.example.rest.webservices.restfulwebservices.helloworld;


import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

//REST API
@RestController
public class HallowWorldController {

    private MessageSource messageSource;

    public HallowWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping(path = "hello-world")
    public  String helloWorld(){
        return "Hello World, welcome";
    }

    //retunring a class or bean
    @GetMapping(path = "hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World, Bean");
    }

    //using path variables to return different paths
    // /ussrs/{id}/todods/{id} => users/2/todos/200: 2 and 200 can be chnaged
    @GetMapping(path = "hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        //return new HelloWorldBean("Hello World Path" + name);
        return new HelloWorldBean(String.format("Hello World, %s",name));
    }

    @GetMapping(path = "hello-world-internationalized")
    public String helloWorldPathVariable(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",
                null, "Default Message", locale);
        //return new HelloWorldInternationalized("Hello World Path" + name);
    }
}
