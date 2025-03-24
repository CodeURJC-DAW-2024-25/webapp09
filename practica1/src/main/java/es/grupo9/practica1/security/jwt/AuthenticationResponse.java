package es.grupo9.practica1.security.jwt;

import java.util.List;

public class AuthenticationResponse {
    
    private String jwt;
    private List<String> roles;

    // Default constructor (required for JSON deserialization)
    public AuthenticationResponse() {}

    // Parameterized constructor
    public AuthenticationResponse(String jwt, List<String> roles) {
        this.jwt = jwt;
        this.roles = roles;
    }

    // Getter and setter
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
    
    
    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
