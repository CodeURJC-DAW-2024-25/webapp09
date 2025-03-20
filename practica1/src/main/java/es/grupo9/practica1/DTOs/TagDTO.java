package es.grupo9.practica1.DTOs;

import es.grupo9.practica1.entities.Tag;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TagDTO {
    private String id;
    
    public TagDTO() {

    }

    public TagDTO(String id) {
        this.id = id;
    }

    public TagDTO(Tag tag) {
        this.id = tag.getId();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
