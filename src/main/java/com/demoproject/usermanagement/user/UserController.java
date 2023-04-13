package com.demoproject.usermanagement.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/")
public class UserController {
    @GetMapping("/")
    public String registrationForm() {
        System.out.println("inside /usr action");
        return "user/user";
    }
}
