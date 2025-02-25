package es.grupo9.practica1;


import jakarta.persistence.*;
//import java.util.List;

@Entity
public class Housing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;

    @Column(name = "location", nullable = false)
    private String location;
    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;
    @Column(name = "image", length = 100)
    private String image;

    public Housing() {
    }

    public Housing(int code, String location, String name, String image) {
        this.code = code;
        this.location = location;
        this.name = name;
        this.image = image;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
