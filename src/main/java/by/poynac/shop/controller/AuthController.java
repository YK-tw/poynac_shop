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

    private final String LOGIN_PATH = "/security/login";
    private final String REGISTER_PATH = "/security/registration";

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/login")
    public String getLoginPage() {
        return LOGIN_PATH;
    }

    @GetMapping("/register")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new UserRegisterWrapper());
        return REGISTER_PATH;
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute(value = "user") UserRegisterWrapper user,
                               Model model) {
        if (user.getPassword().equals(user.getPasswordConfirm())) {
            if (userService.saveUser(userMapper.toEntity(user))) {
                return LOGIN_PATH;
            } else {
                model.addAttribute("error", "register.userExists");
                return REGISTER_PATH;
            }
        }
        model.addAttribute("error", "register.passwordMismatch");
        return REGISTER_PATH;
    }

}
