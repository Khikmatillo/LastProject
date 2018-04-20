package com.example.project.admin.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@Service
public interface CommonsService {
    String searchById(String id, String attribute, JpaRepository repository, Model model);
}
