package com.example.project.admin.controller;

import com.example.project.admin.TempUser;
import com.example.project.entity.User;
import com.example.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String loginPage(Model model){
        model.addAttribute("tempUser", new TempUser());
        return "adminLogin";
    }

    @PostMapping("/index")
    public String indexPage(@Valid TempUser temp, Model model){
        User user = userRepository.findByEmail(temp.getEmail());
        if(user != null){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if(encoder.matches(temp.getPassword(), user.getPassword())){
                if(user.getRole().equals("ROLE_ADMIN")){
                    return "adminIndex";
                }else{
                    model.addAttribute("errorMessage", "Access Denied!");
                }
            }else{
                model.addAttribute("errorMessage", "Incorrect password!");
            }
        }else{
            model.addAttribute("errorMessage", "enter a valid email and password!");
        }
        model.addAttribute("tempUser", new TempUser());

        return "adminLogin";
    }
}
