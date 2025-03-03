package es.grupo9.practica1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;

@Service
public class HousingService {

    @Autowired
    private HousingRepository housingRepository;

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

    // Método para agregar un nuevo hotel
    public Housing addHotel(String location, String name, byte[] imageBytes, Integer stars, Integer price, String description, String tags) {
        // Convertir el array de bytes a Blob
        Blob imageBlob = convertToBlob(imageBytes);
    
        // Obtener el código más alto actual
        Integer hotelCode = housingRepository.maxhotelCode();
        if (hotelCode == null) {
            hotelCode = 0; // Si no hay hoteles, iniciar desde 1
        }
        hotelCode += 1;
    
        // Crear el nuevo hotel y guardarlo
        Housing newHotel = new Housing(hotelCode, location, name, imageBlob, stars, price, description, false, tags);
        return housingRepository.save(newHotel);
    }

    @PostConstruct
    public void initializeHotels() {
        // Inicialización de los hoteles en la base de datos si no existen
        List<Housing> hoteles = Arrays.asList(
            new Housing(1, "Madrid", "Hotel Madrid", convertToBlob(loadImage("room-3.jpg")), 4, 120, "Acogedor hotel en el corazón de Madrid con todas las comodidades.", true, "centrico,comodo"),
            new Housing(2, "Barcelona", "Hotel Barcelona", convertToBlob(loadImage("room-1.jpg")), 5, 150, "Hotel de lujo con vistas al mar Mediterráneo.", true, "lujo,vista"),
            new Housing(3, "Valencia", "Hotel Valencia", convertToBlob(loadImage("room-5.jpg")), 3, 90, "Moderno hotel cerca de la playa y del centro de la ciudad.", true, "moderno,playa"),
            new Housing(4, "París", "Hotel Parisien", convertToBlob(loadImage("room-2.jpg")), 4, 200, "Elegante hotel en el centro de París, cerca de la Torre Eiffel.", true, "elegante,centrico"),
            new Housing(5, "Londres", "Hotel London", convertToBlob(loadImage("room-6.jpg")), 5, 180, "Lujo y confort en el corazón de Londres, con todas las comodidades.", true, "lujo,confort"),
            new Housing(6, "Berlín", "Hotel Berlin", convertToBlob(loadImage("room-4.jpg")), 3, 110, "Hotel moderno con acceso fácil a los principales puntos turísticos de Berlín.", true, "moderno,acceso"),
            new Housing(7, "Roma", "Hotel Roma", convertToBlob(loadImage("room-1.jpg")), 4, 130, "Hotel elegante con una ubicación perfecta en Roma.", true, "elegante,ubicacion"),
            new Housing(8, "Ámsterdam", "Hotel Amsterdam", convertToBlob(loadImage("room-3.jpg")), 4, 140, "Céntrico hotel en Ámsterdam, cerca de museos y restaurantes.", true, "centrico,museos"),
            new Housing(9, "Lisboa", "Hotel Lisboa", convertToBlob(loadImage("room-6.jpg")), 3, 95, "Hotel en Lisboa con ambiente acogedor y excelente servicio.", true, "acogedor,servicio"),
            new Housing(10, "Nueva York", "Hotel Manhattan", convertToBlob(loadImage("room-2.jpg")), 5, 250, "Hotel de lujo en el centro de Manhattan, cerca de Times Square.", true, "lujo,centrico"),
            new Housing(11, "Tokio", "Hotel Tokyo", convertToBlob(loadImage("room-5.jpg")), 5, 220, "Hotel moderno en el distrito de Shibuya, ideal para turistas.", true, "moderno,turistas"),
            new Housing(12, "Sídney", "Hotel Sydney", convertToBlob(loadImage("room-4.jpg")), 4, 190, "Impresionante hotel con vistas a la bahía de Sídney.", true, "vistas,bahia"),
            new Housing(13, "Buenos Aires", "Hotel Buenos Aires", convertToBlob(loadImage("room-3.jpg")), 4, 100, "Hotel económico y cómodo en el centro de Buenos Aires.", true, "economico,comodo"),
            new Housing(14, "Ciudad de México", "Hotel CDMX", convertToBlob(loadImage("room-1.jpg")), 4, 130, "Moderno hotel cerca de los principales puntos turísticos de la ciudad.", true, "moderno,turistico"),
            new Housing(15, "Toronto", "Hotel Toronto", convertToBlob(loadImage("room-6.jpg")), 5, 210, "Hotel de lujo con vistas panorámicas de la ciudad y el lago Ontario.", true, "lujo,vistas"),
            new Housing(16, "Ciudad de prueba", "Hotel prueba", convertToBlob(loadImage("room-6.jpg")), 5, 210, "Hotel de lujo con vistas panorámicas de la ciudad y el lago Ontario.", false, "lujo,vistas"),
            new Housing(17, "Miami", "Hotel Miami", convertToBlob(loadImage("room-1.jpg")), 4, 160, "Hotel frente al mar con acceso a la playa y excelentes instalaciones.", false, "playa,instalaciones"),
            new Housing(18, "Los Ángeles", "Hotel Los Ángeles", convertToBlob(loadImage("room-2.jpg")), 5, 230, "Exclusivo hotel de lujo en el centro de Los Ángeles con piscina.", false, "lujo,piscina"),
            new Housing(19, "Madrid", "Hotel Sol", convertToBlob(loadImage("room-3.jpg")), 3, 100, "Hotel sencillo con buena ubicación y precios accesibles.", false, "sencillo,ubicacion"),
            new Housing(20, "Buenos Aires", "Hotel Del Centro", convertToBlob(loadImage("room-4.jpg")), 4, 140, "Hotel acogedor con excelentes conexiones de transporte.", false, "acogedor,transporte")
        );

        housingRepository.saveAll(hoteles);
    }

    public List<Housing> getAllHousings() {
        return housingRepository.findAll();
    }

    public List<Housing> searchHousing(String tags, Integer stars) {
        Pageable pageable = PageRequest.of(0, 10); // Ajusta el tamaño de la página según sea necesario
        Page<Housing> housingPage = housingRepository.findByStarsAndTags(stars, tags, pageable);
        return housingPage.getContent();
    }
}