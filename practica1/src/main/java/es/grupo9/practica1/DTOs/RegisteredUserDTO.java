package es.grupo9.practica1.DTOs;

import java.util.List;

import es.grupo9.practica1.entities.User;

public class RegisteredUserDTO {

    private String dni;
    private String name;
    private Integer number;
    private String password;
    private String email;
    private Boolean admin;
    private List<String> roles;

    public RegisteredUserDTO() {

    }

    public RegisteredUserDTO(String dni, String name, Integer number,String password ,String email, Boolean admin, List<String> roles) {

        this.dni = dni;
        this.name = name;
        this.password = password;
        this.email = email;
        this.admin = admin;
        this.roles = roles;

    }

    public RegisteredUserDTO(User user) {
        this.dni = user.getDni();
        this.name = user.getName();
        this.password = user.getPassword();
        this.number = user.getNumber();
        this.email = user.getEmail();
        this.admin = user.getAdmin();
        this.roles = user.getRoles();
    }

    // Getters and Setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegisteredUserDTO{" +
                "dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", email='" + email + '\'' +
                ", admin=" + admin +
                ", password=" + password +
                ", roles=" + roles +
                '}';
    }
    
}
