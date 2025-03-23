package es.grupo9.practica1.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Tag {
    
    @Id
    private String id;
    

    public Tag() {}
    public Tag( String id) {
        this.id = id;
    }

    
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
