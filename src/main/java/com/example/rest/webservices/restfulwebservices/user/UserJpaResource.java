package com.example.rest.webservices.restfulwebservices.user;

import com.example.rest.webservices.restfulwebservices.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class UserJpaResource {

    private UserRepository repository;

    public UserJpaResource(UserDaoService service, UserRepository repository) {

        this.repository = repository;
    }

    @GetMapping(path = "jpa/users")
    public List<User> retrieveAllUsers(){
        return repository.findAll();
    }

    @GetMapping(path = "jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        Optional<User> user = repository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);

        EntityModel<User> entityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;

    }

    @DeleteMapping(path = "jpa/users/{id}")
    public void deleteUser(@PathVariable int id){

        repository.deleteById(id);
    }

    //create a user using post request
    @PostMapping("jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser =repository.save(user);
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
