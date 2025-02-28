package es.grupo9.practica1;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Housing addHotel(String location, String name, byte[] imageBytes, Integer stars, Integer price, String description) {
        // Convertir el array de bytes a Blob
        Blob imageBlob = convertToBlob(imageBytes);
    
        // Obtener el código más alto actual
        Integer hotelCode = housingRepository.maxhotelCode();
        if (hotelCode == null) {
            hotelCode = 0; // Si no hay hoteles, iniciar desde 1
        }
        hotelCode += 1;
    
        // Crear el nuevo hotel y guardarlo
        Housing newHotel = new Housing(hotelCode, location, name, imageBlob, stars, price, description, false);
        return housingRepository.save(newHotel);
    }
    

    @PostConstruct
    public void initializeHotels() {
        // Inicialización de los hoteles en la base de datos si no existen
        List<Housing> hoteles = Arrays.asList(
            new Housing(1, "Madrid", "Hotel Madrid", convertToBlob(loadImage("room-3.jpg")), 4, 120, "Acogedor hotel en el corazón de Madrid con todas las comodidades.", true),
            new Housing(2, "Barcelona", "Hotel Barcelona", convertToBlob(loadImage("room-1.jpg")), 5, 150, "Hotel de lujo con vistas al mar Mediterráneo.", true),
            new Housing(3, "Valencia", "Hotel Valencia", convertToBlob(loadImage("room-5.jpg")), 3, 90, "Moderno hotel cerca de la playa y del centro de la ciudad.", true),
            new Housing(4, "París", "Hotel Parisien", convertToBlob(loadImage("room-2.jpg")), 4, 200, "Elegante hotel en el centro de París, cerca de la Torre Eiffel.", true),
            new Housing(5, "Londres", "Hotel London", convertToBlob(loadImage("room-6.jpg")), 5, 180, "Lujo y confort en el corazón de Londres, con todas las comodidades.", true),
            new Housing(6, "Berlín", "Hotel Berlin", convertToBlob(loadImage("room-4.jpg")), 3, 110, "Hotel moderno con acceso fácil a los principales puntos turísticos de Berlín.", true),
            new Housing(7, "Roma", "Hotel Roma", convertToBlob(loadImage("room-1.jpg")), 4, 130, "Hotel elegante con una ubicación perfecta en Roma.", true),
            new Housing(8, "Ámsterdam", "Hotel Amsterdam", convertToBlob(loadImage("room-3.jpg")), 4, 140, "Céntrico hotel en Ámsterdam, cerca de museos y restaurantes.", true),
            new Housing(9, "Lisboa", "Hotel Lisboa", convertToBlob(loadImage("room-6.jpg")), 3, 95, "Hotel en Lisboa con ambiente acogedor y excelente servicio.", true),
            new Housing(10, "Nueva York", "Hotel Manhattan", convertToBlob(loadImage("room-2.jpg")), 5, 250, "Hotel de lujo en el centro de Manhattan, cerca de Times Square.", true),
            new Housing(11, "Tokio", "Hotel Tokyo", convertToBlob(loadImage("room-5.jpg")), 5, 220, "Hotel moderno en el distrito de Shibuya, ideal para turistas.", true),
            new Housing(12, "Sídney", "Hotel Sydney", convertToBlob(loadImage("room-4.jpg")), 4, 190, "Impresionante hotel con vistas a la bahía de Sídney.", true),
            new Housing(13, "Buenos Aires", "Hotel Buenos Aires", convertToBlob(loadImage("room-3.jpg")), 4, 100, "Hotel económico y cómodo en el centro de Buenos Aires.", true),
            new Housing(14, "Ciudad de México", "Hotel CDMX", convertToBlob(loadImage("room-1.jpg")), 4, 130, "Moderno hotel cerca de los principales puntos turísticos de la ciudad.", true),
            new Housing(15, "Toronto", "Hotel Toronto", convertToBlob(loadImage("room-6.jpg")), 5, 210, "Hotel de lujo con vistas panorámicas de la ciudad y el lago Ontario.", true)
        );

        housingRepository.saveAll(hoteles);
    }

    public List<Housing> getAllHousings() {
        return housingRepository.findAll();
    }
}