package spring.springbasic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") //localhouse:8080 /
    public String home(){
        return "home";
    }

}
