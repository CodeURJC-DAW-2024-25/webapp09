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
    public Housing addHotel(String location, String name, byte[] image) {
        // Obtener el código más alto actual
        Integer hotelCode = housingRepository.maxhotelCode();
        if (hotelCode == null) {
            hotelCode = 0; // Si no hay hoteles en la base de datos, iniciar desde 1
        }
        hotelCode += 1; // Incrementamos el código para el nuevo hotel

        // Crear el nuevo hotel y guardarlo
        Housing newHotel = new Housing(hotelCode, location, name, image);
        return housingRepository.save(newHotel);
    }

    @PostConstruct
    public void initializeHotels() {
        // Inicialización de los hoteles en la base de datos si no existen
        List<Housing> hoteles = Arrays.asList(
            new Housing(1, "Madrid", "Hotel Madrid", loadImage("room-3.jpg")),
            new Housing(2, "Barcelona", "Hotel Barcelona", loadImage("room-1.jpg")),
            new Housing(3, "Valencia", "Hotel Valencia", loadImage("room-5.jpg")),
            new Housing(4, "París", "Hotel Parisien", loadImage("room-2.jpg")),
            new Housing(5, "Londres", "Hotel London", loadImage("room-6.jpg")),
            new Housing(6, "Berlín", "Hotel Berlin", loadImage("room-4.jpg")),
            new Housing(7, "Roma", "Hotel Roma", loadImage("room-1.jpg")),
            new Housing(8, "Ámsterdam", "Hotel Amsterdam", loadImage("room-3.jpg")),
            new Housing(9, "Lisboa", "Hotel Lisboa", loadImage("room-6.jpg")),
            new Housing(10, "Nueva York", "Hotel Manhattan", loadImage("room-2.jpg")),
            new Housing(11, "Tokio", "Hotel Tokyo", loadImage("room-5.jpg")),
            new Housing(12, "Sídney", "Hotel Sydney", loadImage("room-4.jpg")),
            new Housing(13, "Buenos Aires", "Hotel Buenos Aires", loadImage("room-3.jpg")),
            new Housing(14, "Ciudad de México", "Hotel CDMX", loadImage("room-1.jpg")),
            new Housing(15, "Toronto", "Hotel Toronto", loadImage("room-6.jpg"))
        );

        housingRepository.saveAll(hoteles);
    }


    public List<Housing> getAllHousings(){


        return housingRepository.findAll();
    }
}