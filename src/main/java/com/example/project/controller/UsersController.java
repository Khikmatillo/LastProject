package com.example.project.controller;

import com.example.project.entity.User;
import com.example.project.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping
    public ObjectNode registerNewUser(@RequestBody ObjectNode json){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String email = json.get("email").textValue();
        String password = json.get("password").textValue();
        String role = json.get("role").textValue();

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();

        List<String> emails = userRepository.findEmails();

        if(emails.contains(email)){
            node.put("status", "409");
            node.put("message", "Mail already exists!");
        }else{
            User user = userRepository.save(new User(email, bCryptPasswordEncoder.encode(password), role));
            node.put("status", "201");
            node.put("message", "Successfully created user");
            node.put("id", user.getId());
            node.put("email", email);
            node.put("role", role);
        }


        return node;
    }

}
