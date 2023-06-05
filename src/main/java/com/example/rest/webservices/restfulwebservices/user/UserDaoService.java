package com.example.rest.webservices.restfulwebservices.user;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    // To be able to retreive all users, save user and find one user

    private static List<User> users = new ArrayList<>();
    private static Integer usersCounts=0;

    //creates a static list that is generated at run time
    static {
        users.add(new User(++usersCounts, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCounts, "Eve", LocalDate.now().minusYears(25)));
        users.add(new User(++usersCounts, "Jim", LocalDate.now().minusYears(20)));
    }

    //returns all the users
    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        user.setId(++usersCounts);
        users.add(user);
        return user;

    }

    public User findOne(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }



}
