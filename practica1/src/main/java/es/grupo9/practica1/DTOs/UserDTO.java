package es.grupo9.practica1.DTOs;

import java.util.List;

import es.grupo9.practica1.entities.User;
import io.swagger.v3.oas.annotations.media.Schema;

public class UserDTO {
    private String dni;
    private String name;
    @Schema(description = "User's phone number", example = "111322999", pattern = "^\\d{9}$")
    private Integer number;
    private String email;
    private Boolean admin;
    private List<String> roles;

    public UserDTO() {

    }

    public UserDTO(String dni, String name, Integer number, String email, Boolean admin, List<String> roles) {

        this.dni = dni;
        this.name = name;
        this.email = email;
        this.admin = admin;
        this.roles = roles;

    }

    public UserDTO(User user) {
        this.dni = user.getDni();
        this.name = user.getName();
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

    @Override
    public String toString() {
        return "UserDTO{" +
                "dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", email='" + email + '\'' +
                ", admin=" + admin +
                ", roles=" + roles +
                '}';
    }

}
