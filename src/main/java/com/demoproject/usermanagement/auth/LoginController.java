package com.demoproject.usermanagement.auth;


import com.demoproject.usermanagement.user.User;
import com.demoproject.usermanagement.user.UserDTO;
import com.demoproject.usermanagement.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String loginForm() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registration(
            @Valid @ModelAttribute("user") UserDTO userDto,
            BindingResult result,
            Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null)
            result.rejectValue("email", null,
                    "User already registered !!!");

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "auth/registration";
        }

        userService.saveUser(userDto);
        return "redirect:/registration?success";
    }
}