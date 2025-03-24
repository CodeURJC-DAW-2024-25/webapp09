package es.grupo9.practica1.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import es.grupo9.practica1.entities.User;
import java.util.List;

public final class UserDTO {

    // Campos
    private String dni;
    private String name;
    
    @Schema(description = "User's phone number", example = "111322999", pattern = "^\\d{9}$")
    private Integer number;
    
    private String email;
    private Boolean admin;
    private List<String> roles;

    // Constructores
    public UserDTO() {}

    public UserDTO(
        String dni, 
        String name, 
        Integer number, 
        String email, 
        Boolean admin, 
        List<String> roles
    ) {
        this.dni = dni;
        this.name = name;
        this.number = number;
        this.email = email;
        this.admin = admin;
        this.roles = roles;
    }

    public UserDTO(User user) {
        this(
            user.getDni(),
            user.getName(),
            user.getNumber(),
            user.getEmail(),
            user.getAdmin(),
            user.getRoles()
        );
    }

    // Getters
    public String getDni() { return this.dni; }
    public String getName() { return this.name; }
    public Integer getNumber() { return this.number; }
    public String getEmail() { return this.email; }
    public Boolean getAdmin() { return this.admin; }
    public List<String> getRoles() { return this.roles; }

    // Setters
    public void setDni(String dni) { this.dni = dni; }
    public void setName(String name) { this.name = name; }
    public void setNumber(Integer number) { this.number = number; }
    public void setEmail(String email) { this.email = email; }
    public void setAdmin(Boolean admin) { this.admin = admin; }
    public void setRoles(List<String> roles) { this.roles = roles; }

    // Representación como String
    @Override
    public String toString() {
        return new StringBuilder("UserDTO{")
            .append("dni='").append(dni).append("', ")
            .append("name='").append(name).append("', ")
            .append("number=").append(number).append(", ")
            .append("email='").append(email).append("', ")
            .append("admin=").append(admin).append(", ")
            .append("roles=").append(roles)
            .append('}')
            .toString();
    }
}