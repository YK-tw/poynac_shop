package by.poynac.shop.mapper;

import by.poynac.shop.model.User;
import by.poynac.shop.model.wrapper.UserRegisterWrapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRegisterWrapper user);

}
