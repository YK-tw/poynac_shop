package by.poynac.shop.model;

import by.poynac.shop.model.enumeration.Role;
import by.poynac.shop.model.enumeration.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String firstname;

    private String lastname;

    private String phone;

    private String address;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    public User(Long id, String email, String password, String firstname, String lastname, String phone, String address, Role role, Status status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.status = status;
    }

    public User() {
    }

}
