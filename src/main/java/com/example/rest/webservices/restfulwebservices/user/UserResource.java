package com.example.rest.webservices.restfulwebservices.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
        User user = service.findOne(id);
        if(user ==null)
            throw new UserNotFoundException("id:"+id);
        return user;

    }

    @DeleteMapping(path = "users/{id}")
    public void deleteUser(@PathVariable int id){
        service.deleteById(id);
    }

    //create a user using post request
    @PostMapping("users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser =service.save(user);
        //users/4 => /users /{id}, user.getID
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        //used to change response
        return ResponseEntity.created(location).build();

    }

}
