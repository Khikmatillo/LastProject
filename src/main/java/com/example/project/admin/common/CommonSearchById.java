package com.example.project.admin.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

public class CommonSearchById  implements CommonsService{
    @Override
    public String searchById(String id, String attribute, JpaRepository repository, Model model) {
        if(id.isEmpty()){
            model.addAttribute(attribute, repository.findAll());
            model.addAttribute("errorMessage", "enter an ID first!");
            return "adminIndex";
        }
        int objid = Integer.parseInt(id);
        Optional optional = repository.findById(objid);
        if(optional.isPresent()){
            model.addAttribute(attribute, optional.get());
        }else{
            model.addAttribute(attribute, repository.findAll());
            model.addAttribute("errorMessage", "entered " + attribute + " ID does not exist!");
        }

        return "adminIndex";
    }

}
