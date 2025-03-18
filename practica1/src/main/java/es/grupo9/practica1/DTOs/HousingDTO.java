package es.grupo9.practica1.DTOs;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Set;

import javax.sql.rowset.serial.SerialBlob;

import es.grupo9.practica1.entities.Housing;
import es.grupo9.practica1.entities.Tag;

public class HousingDTO {
    private int code;
    private String location;
    private String name;
    private String imageBase64; // Store the image as a Base64 string
    private Integer price;
    private String description;
    private Integer stars;
    private Boolean acepted;
    private Set<Tag> tags;



    // Constructors
    public HousingDTO(int code, String location, String name, String imageBase64, Integer price, String description,
        Integer stars, Boolean acepted, Set<Tag> tags) {
        this.code = code;
        this.location = location;
        this.name = name;
        this.imageBase64 = imageBase64;
        this.price = price;
        this.description = description;
        this.stars = stars;
        this.acepted = acepted;
        this.tags = tags;
    }

    public HousingDTO(Housing house){
        this.code = house.getCode();
        this.location = house.getLocation();
        this.name = house.getName();
        this.imageBase64 = house.getImageBase64();
        this.price = house.getPrice();
        this.description = house.getDescription();
        this.stars = house.getStars();
        this.acepted = house.getAcepted();
        this.tags = house.getTags();
    }

    
    public HousingDTO() {
    }

    // Getters and Setters
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

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
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

    public Boolean getAcepted() {
        return acepted;
    }

    public void setAcepted(Boolean acepted) {
        this.acepted = acepted;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
    private static Blob convertBase64ToBlob(String imageBase64) {
        if (imageBase64 == null || imageBase64.isEmpty()) {
            return null;
        }
        try {
            byte[] imageBytes = Base64.getDecoder().decode(imageBase64);
            return new SerialBlob(imageBytes);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to convert Base64 to Blob", e);
        }
    }

    public Blob obtainImage(String image) {
        return convertBase64ToBlob(image);
    }
}
