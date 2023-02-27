package org.spring.teamproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @GetMapping("/")
    public String mainView(){
        return "/pages/MainIndex";
    }

    @GetMapping("/login")
    public String loginView(){
        return "/pages/login";
    }

    @GetMapping("/join")
    public String joinView(Model model){

        return "/pages/join";
    }


}
