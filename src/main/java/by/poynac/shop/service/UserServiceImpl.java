package by.poynac.shop.service;

import by.poynac.shop.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public boolean save(User user) {
        return false;
    }
}
