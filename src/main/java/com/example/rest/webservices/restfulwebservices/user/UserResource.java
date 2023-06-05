package com.example.rest.webservices.restfulwebservices.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserResource {

    private UserDaoService service;

    public UserResource(UserDaoService service) {
        this.service = service;
    }

    @GetMapping(path = "users")
    public List<User> retrieveAllUsers(){
        return service.findAll();

    }

    @GetMapping(path = "users/{id}")
    public User retrieveUser(@PathVariable int id){
        return service.findOne(id);

    }

    //create a user using post request
    @PostMapping("users")
    public void createUser(){

    }

}
