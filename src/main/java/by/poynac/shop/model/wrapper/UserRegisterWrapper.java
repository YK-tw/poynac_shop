package by.poynac.shop.model.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterWrapper {

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private String passwordConfirm;

    private String phone;

    private String address;

}
