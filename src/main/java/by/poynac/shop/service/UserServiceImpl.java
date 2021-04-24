package by.poynac.shop.service;

import by.poynac.shop.model.User;
import by.poynac.shop.model.enumeration.Role;
import by.poynac.shop.model.enumeration.Status;
import by.poynac.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean saveUser(User user) {
        if (user == null) {
            return false;
        }
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user).equals(user);
    }
}
