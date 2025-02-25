package es.grupo9.practica1;

import java.util.Base64;

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
    private byte[] image; // Ahora la imagen se almacena como un array de bytes (BLOB)

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Column(name = "stars", nullable = false)
    private Integer stars;

    public Housing() {
    }

    public Housing(int code, String location, String name, byte[] image, Integer stars, Integer price, String description) {
        this.code = code;
        this.location = location;
        this.name = name;
        this.image = image;
        this.stars = stars;
        this.price = price;
        this.description = description;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
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
        if (image == null || image.length == 0) {
            return ""; // Return an empty string if the image is null or empty
        }
        return Base64.getEncoder().encodeToString(image); // Convert byte[] to Base64
    }
}
