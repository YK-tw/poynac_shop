package by.poynac.shop.model;

import by.poynac.shop.model.enumeration.Role;
import by.poynac.shop.model.enumeration.Status;

import javax.persistence.*;

@Entity
@Table(name = "user")
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

    public Long getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public Role getRole() {
        return this.role;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
