package es.grupo9.practica1.entities;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Housing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    @JsonIgnore
    private Blob image; // Ahora la imagen se almacena como un array de bytes (LONGBLOB)

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Column(name = "stars", nullable = false)
    private Integer stars;

    @Column(name = "acepted", nullable = false)
    protected Boolean acepted;

    @ManyToMany
    @JoinTable(
        name = "housing_tags",
        joinColumns = @JoinColumn(name = "housing_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    public Housing() {
    }

    public Housing(int code, String location, String name, Blob image, Integer stars, Integer price, String description, Boolean acepted, Set<Tag> tags) {
        this.code = code;
        this.location = location;
        this.name = name;
        this.image = image;
        this.stars = stars;
        this.price = price;
        this.description = description;
        this.acepted = acepted;
        this.tags = tags;
    }

    public Boolean getAcepted() {
        return acepted;
    }

    public void setAcepted(Boolean acepted) {
        this.acepted = acepted;
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

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getImageBase64() {
        if (image == null) {
            return ""; // Retorna una cadena vacía si no hay imagen
        }
        try {
            byte[] imageBytes = image.getBytes(1, (int) image.length());
            return Base64.getEncoder().encodeToString(imageBytes); // Convertir a Base64
        } catch (SQLException e) {
            e.printStackTrace();
            return ""; // Retorna una cadena vacía en caso de error
        }
    }
    public Set<Tag> getTags() { return tags; }
    public void setTags(Set<Tag> tags) { this.tags = tags; }
}