package by.poynac.shop.controller;

import by.poynac.shop.mapper.UserMapper;
import by.poynac.shop.model.wrapper.UserRegisterWrapper;
import by.poynac.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/login")
    public String getLoginPage() {
        return "/security/login";
    }

    @GetMapping("/register")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new UserRegisterWrapper());
        return "/security/registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute(value = "user") UserRegisterWrapper user) {
        userService.save(userMapper.toEntity(user));
        return "/security/login";
    }

}
