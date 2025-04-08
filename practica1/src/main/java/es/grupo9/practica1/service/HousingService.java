package es.grupo9.practica1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.grupo9.practica1.DTOs.HousingDTO;
import es.grupo9.practica1.DTOs.TagDTO;

import es.grupo9.practica1.entities.Housing;
import es.grupo9.practica1.entities.Tag;

import es.grupo9.practica1.repository.HousingRepository;
import es.grupo9.practica1.repository.TagRepository;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.io.InputStream;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.sql.rowset.serial.SerialBlob;

@Service
public class HousingService {

    @Autowired
    private HousingRepository housingRepository;

    @Autowired
    private TagRepository tagRepository;

    // Método para cargar las imágenes desde recursos
    private byte[] loadImage(String imageName) {
        try (InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("static/img/" + imageName)) {
            if (inputStream == null) {
                System.err.println("No se encontró la imagen: " + imageName);
                return new byte[0]; // Retorna un array vacío si la imagen no se encuentra
            }
            return inputStream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    // Método para convertir byte[] a Blob
    private Blob convertToBlob(byte[] imageBytes) {
        try {
            return new SerialBlob(imageBytes);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



    @PostConstruct
    public void initializeHotels() {
        Tag beach = tagRepository.findById("Beach").orElse(new Tag("Beach"));
        Tag city = tagRepository.findById("City").orElse(new Tag("City"));
        Tag river = tagRepository.findById("River").orElse(new Tag("River"));

        tagRepository.saveAll(Set.of(beach, city, river));
        // Inicialización de los hoteles en la base de datos si no existen
        List<Housing> hoteles = Arrays.asList(
                new Housing(1, "Madrid", "Hotel Madrid", convertToBlob(loadImage("room-10.jpg")), 4, 120,
                        "Acogedor hotel en el corazón de Madrid con todas las comodidades.", true, Set.of(beach, city)),
                new Housing(2, "Barcelona", "Hotel Barcelona", convertToBlob(loadImage("room-11.jpg")), 5, 150,
                        "Hotel de lujo con vistas al mar Mediterráneo.", true, Set.of(beach, river)),
                new Housing(3, "Valencia", "Hotel Valencia", convertToBlob(loadImage("room-12.jpg")), 3, 90,
                        "Moderno hotel cerca de la playa y del centro de la ciudad.", true, Set.of(beach, city)),
                new Housing(4, "París", "Hotel Parisien", convertToBlob(loadImage("room-13.jpg")), 4, 200,
                        "Elegante hotel en el centro de París, cerca de la Torre Eiffel.", true, Set.of(beach, river)),
                new Housing(5, "Londres", "Hotel London", convertToBlob(loadImage("room-14.jpg")), 5, 180,
                        "Lujo y confort en el corazón de Londres, con todas las comodidades.", true,
                        Set.of(beach, city)),
                new Housing(6, "Berlín", "Hotel Berlin", convertToBlob(loadImage("room-15.jpg")), 3, 110,
                        "Hotel moderno con acceso fácil a los principales puntos turísticos de Berlín.", true,
                        Set.of(beach, river)),
                new Housing(7, "Roma", "Hotel Roma", convertToBlob(loadImage("room-9.jpg")), 4, 130,
                        "Hotel elegante con una ubicación perfecta en Roma.", true, Set.of(beach, river)),
                new Housing(8, "Ámsterdam", "Hotel Amsterdam", convertToBlob(loadImage("room-8.jpg")), 4, 140,
                        "Céntrico hotel en Ámsterdam, cerca de museos y restaurantes.", true, Set.of(beach, city)),
                new Housing(9, "Lisboa", "Hotel Lisboa", convertToBlob(loadImage("room-7.jpg")), 3, 95,
                        "Hotel en Lisboa con ambiente acogedor y excelente servicio.", true, Set.of(beach)),
                new Housing(10, "Nueva York", "Hotel Manhattan", convertToBlob(loadImage("room-9.jpg")), 5, 250,
                        "Hotel de lujo en el centro de Manhattan, cerca de Times Square.", true, Set.of(beach, city)),
                new Housing(11, "Tokio", "Hotel Tokyo", convertToBlob(loadImage("room-9.jpg")), 5, 220,
                        "Hotel moderno en el distrito de Shibuya, ideal para turistas.", true, Set.of(beach)),
                new Housing(12, "Sídney", "Hotel Sydney", convertToBlob(loadImage("room-8.jpg")), 4, 190,
                        "Impresionante hotel con vistas a la bahía de Sídney.", true, Set.of(beach, city)),
                new Housing(13, "Buenos Aires", "Hotel Buenos Aires", convertToBlob(loadImage("room-7.jpg")), 4, 100,
                        "Hotel económico y cómodo en el centro de Buenos Aires.", true, Set.of(beach)),
                new Housing(14, "Ciudad de México", "Hotel CDMX", convertToBlob(loadImage("room-9.jpg")), 4, 130,
                        "Moderno hotel cerca de los principales puntos turísticos de la ciudad.", true,
                        Set.of(beach, city)),
                new Housing(15, "Toronto", "Hotel Toronto", convertToBlob(loadImage("room-14.jpg")), 5, 210,
                        "Hotel de lujo con vistas panorámicas de la ciudad y el lago Ontario.", true, Set.of(beach)),
                new Housing(16, "Ciudad de prueba", "Hotel prueba", convertToBlob(loadImage("room-13.jpg")), 5, 210,
                        "Hotel de lujo con vistas panorámicas de la ciudad y el lago Ontario.", false, Set.of(beach)),
                new Housing(17, "Miami", "Hotel Miami", convertToBlob(loadImage("room-12.jpg")), 4, 160,
                        "Hotel frente al mar con acceso a la playa y excelentes instalaciones.", false,
                        Set.of(beach, city)),
                new Housing(18, "Los Ángeles", "Hotel Los Ángeles", convertToBlob(loadImage("room-11.jpg")), 5, 230,
                        "Exclusivo hotel de lujo en el centro de Los Ángeles con piscina.", false, Set.of(beach)),
                new Housing(19, "Madrid", "Hotel Sol", convertToBlob(loadImage("room-13.jpg")), 3, 100,
                        "Hotel sencillo con buena ubicación y precios accesibles.", false, Set.of(beach, city)),
                new Housing(20, "Buenos Aires", "Hotel Del Centro", convertToBlob(loadImage("room-15.jpg")), 4, 140,
                        "Hotel acogedor con excelentes conexiones de transporte.", false, Set.of(beach)));

        housingRepository.saveAll(hoteles);
    }

    public List<Housing> getAllHousings() {
        return housingRepository.findAll();
    }

    public List<Housing> findHousesByTagsAndStars(Set<String> tagNames, Integer stars) {
        if (tagNames == null || tagNames.isEmpty()) {
            // If no tags are provided, filter only by stars
            return housingRepository.findByStarsGreaterThanEqual(stars);
        } else {
            // If tags are provided, filter by both tags and stars
            return housingRepository.findByTagsAndStars(tagNames, stars, (long) tagNames.size());
        }
    }

    public List<HousingDTO> getAllHouses() {
        List<Housing> houses = housingRepository.findAll();
        List<HousingDTO> housesDTOs = new ArrayList<>();
        for (Housing house : houses) {
            HousingDTO nuevoHouse = new HousingDTO(house);
            housesDTOs.add(nuevoHouse);
        }
        return housesDTOs;

    }

    public HousingDTO getHouseById(Integer id) {

        Optional<Housing> house = housingRepository.findById(id);

        return new HousingDTO(house.get());

    }

    public HousingDTO createHouse(HousingDTO house) {
        Housing newHouse = new Housing(house.getCode(), house.getLocation(), house.getName(),
            house.obtainImage(house.getImageBase64()), house.getStars(), house.getPrice(), house.getDescription(),
            house.getAcepted(), house.getTags());

        Integer hotelCode = housingRepository.maxhotelCode();
        if (hotelCode == null) {
            hotelCode = 0; // Si no hay hoteles, iniciar desde 1
        }
        hotelCode += 1;
        newHouse.setCode(hashCode());
        housingRepository.save(newHouse);
        return new HousingDTO(newHouse);
    }

    public HousingDTO updateHouse(int code, HousingDTO house) {
        Optional<Housing> originalHouse = housingRepository.findByCode(code);
        Housing finalOriginalHouse = originalHouse.get();

        finalOriginalHouse.setCode(house.getCode());
        finalOriginalHouse.setLocation(house.getLocation());
        finalOriginalHouse.setName(house.getName());
        finalOriginalHouse.setImage(house.obtainImage(house.getImageBase64()));
        finalOriginalHouse.setStars(house.getStars());
        finalOriginalHouse.setPrice(house.getPrice());
        finalOriginalHouse.setDescription(house.getDescription());
        finalOriginalHouse.setAcepted(house.getAcepted());
        finalOriginalHouse.setTags(house.getTags());

        housingRepository.save(finalOriginalHouse);
        return new HousingDTO(finalOriginalHouse);
    }

    public void deleteHouse(int code) {
        housingRepository.deleteById(code); // needs verification

    }

    public Set<TagDTO> getTagsById(int id){
        Optional<Housing> house = housingRepository.findById(id);
        Set<Tag> tags = house.get().getTags();
        Set<TagDTO> tagDTOs = tags.stream()
        .map(tag -> new TagDTO(tag.getId())) // Convert each Tag to TagDTO
        .collect(Collectors.toSet()); // Collect into a Set

        return tagDTOs;
    }

    public Housing findByCode(int code){
        return housingRepository.findByCode(code).get();
    }

    public void saveImage(Housing house){
        

        housingRepository.save(house);
    }

    public void deleteImage(int code){

        Housing houseWithImage = housingRepository.findByCode(code).get();
        houseWithImage.setImage(null);
        housingRepository.save(houseWithImage);
    }

    public Page<HousingDTO> findByAceptedTrue(Pageable pageable){
        Page<Housing> houses = housingRepository.findByAceptedTrue(pageable);
            
    // Convert Page<Housing> to Page<HousingDTO>
    return houses.map(housing -> {
        HousingDTO dto = new HousingDTO();
        // Map simple fields
        dto.setCode(housing.getCode());
        dto.setLocation(housing.getLocation());
        dto.setName(housing.getName());
        dto.setPrice(housing.getPrice());
        dto.setDescription(housing.getDescription());
        dto.setStars(housing.getStars());
        dto.setAcepted(housing.getAcepted());
        
        // Handle image conversion
        try {
            if (housing.getImage() != null) {
                dto.setImageBase64(housing.getImageBase64());
            } else {
                dto.setImageBase64(""); // or null if preferred
            }
        } catch (Exception e) {
            dto.setImageBase64(""); // fallback for error cases
        }
        
        // Map tags (direct entity reference - consider TagDTO if needed)
        dto.setTags(housing.getTags());
        
        return dto;
    });
    }

    public Page<HousingDTO> findByAceptedFalse(Pageable pageable){
        Page<Housing> houses = housingRepository.findByAceptedFalse(pageable);
            
    // Convert Page<Housing> to Page<HousingDTO>
    return houses.map(housing -> {
        HousingDTO dto = new HousingDTO();
        // Map simple fields
        dto.setCode(housing.getCode());
        dto.setLocation(housing.getLocation());
        dto.setName(housing.getName());
        dto.setPrice(housing.getPrice());
        dto.setDescription(housing.getDescription());
        dto.setStars(housing.getStars());
        dto.setAcepted(housing.getAcepted());
        
        // Handle image conversion
        try {
            if (housing.getImage() != null) {
                dto.setImageBase64(housing.getImageBase64());
            } else {
                dto.setImageBase64(""); // or null if preferred
            }
        } catch (Exception e) {
            dto.setImageBase64(""); // fallback for error cases
        }
        
        // Map tags (direct entity reference - consider TagDTO if needed)
        dto.setTags(housing.getTags());
        
        return dto;
    });
    }

    public void deleteById(int code){
        if (housingRepository.existsById(code)) {
            housingRepository.deleteById(code);
            
        } 
        

    }

    public void acceptHouse(int code){
        Housing house = housingRepository.findByCode(code).get();
        house.setAcepted(true);
        housingRepository.save(house);


    }
}