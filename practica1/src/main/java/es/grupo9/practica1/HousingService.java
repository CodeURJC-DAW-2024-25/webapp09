package es.grupo9.practica1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

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

    // Método para agregar un nuevo hotel
    public Housing addHotel(String location, String name, byte[] image, Integer stars, Integer price, String description) {
        // Obtener el código más alto actual
        Integer hotelCode = housingRepository.maxhotelCode();
        if (hotelCode == null) {
            hotelCode = 0; // Si no hay hoteles en la base de datos, iniciar desde 1
        }
        hotelCode += 1; // Incrementamos el código para el nuevo hotel

        // Crear el nuevo hotel y guardarlo
        Housing newHotel = new Housing(hotelCode, location, name, image, stars, price, description);
        return housingRepository.save(newHotel);
    }

    @PostConstruct
    public void initializeHotels() {
        // Inicialización de los hoteles en la base de datos si no existen
        List<Housing> hoteles = Arrays.asList(
            new Housing(1, "Madrid", "Hotel Madrid", loadImage("room-3.jpg"), 4, 120.50, "Acogedor hotel en el corazón de Madrid con todas las comodidades."),
            new Housing(2, "Barcelona", "Hotel Barcelona", loadImage("room-1.jpg"), 5, 150.00, "Hotel de lujo con vistas al mar Mediterráneo."),
            new Housing(3, "Valencia", "Hotel Valencia", loadImage("room-5.jpg"), 3, 90.00, "Moderno hotel cerca de la playa y del centro de la ciudad."),
            new Housing(4, "París", "Hotel Parisien", loadImage("room-2.jpg"), 4, 200.00, "Elegante hotel en el centro de París, cerca de la Torre Eiffel."),
            new Housing(5, "Londres", "Hotel London", loadImage("room-6.jpg"), 5, 180.00, "Lujo y confort en el corazón de Londres, con todas las comodidades."),
            new Housing(6, "Berlín", "Hotel Berlin", loadImage("room-4.jpg"), 3, 110.00, "Hotel moderno con acceso fácil a los principales puntos turísticos de Berlín."),
            new Housing(7, "Roma", "Hotel Roma", loadImage("room-1.jpg"), 4, 130.00, "Hotel elegante con una ubicación perfecta en Roma."),
            new Housing(8, "Ámsterdam", "Hotel Amsterdam", loadImage("room-3.jpg"), 4, 140.00, "Céntrico hotel en Ámsterdam, cerca de museos y restaurantes."),
            new Housing(9, "Lisboa", "Hotel Lisboa", loadImage("room-6.jpg"), 3, 95.00, "Hotel en Lisboa con ambiente acogedor y excelente servicio."),
            new Housing(10, "Nueva York", "Hotel Manhattan", loadImage("room-2.jpg"), 5, 250.00, "Hotel de lujo en el centro de Manhattan, cerca de Times Square."),
            new Housing(11, "Tokio", "Hotel Tokyo", loadImage("room-5.jpg"), 5, 220.00, "Hotel moderno en el distrito de Shibuya, ideal para turistas."),
            new Housing(12, "Sídney", "Hotel Sydney", loadImage("room-4.jpg"), 4, 190.00, "Impresionante hotel con vistas a la bahía de Sídney."),
            new Housing(13, "Buenos Aires", "Hotel Buenos Aires", loadImage("room-3.jpg"), 4, 100.00, "Hotel económico y cómodo en el centro de Buenos Aires."),
            new Housing(14, "Ciudad de México", "Hotel CDMX", loadImage("room-1.jpg"), 4, 130.00, "Moderno hotel cerca de los principales puntos turísticos de la ciudad."),
            new Housing(15, "Toronto", "Hotel Toronto", loadImage("room-6.jpg"), 5, 210.00, "Hotel de lujo con vistas panorámicas de la ciudad y el lago Ontario.")
        );

        housingRepository.saveAll(hoteles);
    }
}