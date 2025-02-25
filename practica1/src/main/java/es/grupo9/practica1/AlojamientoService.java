package es.grupo9.practica1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class AlojamientoService {

    @Autowired
    private AlojamientoRepository alojamientoRepository;

    private int codHotelCounter = 4;
    

    public Alojamiento addHotel(String ubicacion, String nombre, String imagen) {
        Alojamiento newHotel = new Alojamiento(codHotelCounter, ubicacion, nombre, imagen);
        codHotelCounter += 1;
        return alojamientoRepository.save(newHotel);
    }

    @Transactional
    public void initializeHotels() {
        List<Alojamiento> hoteles = Arrays.asList(
            new Alojamiento(1, "Madrid", "Hotel Madrid", "imagen1.jpg"),
            new Alojamiento(2, "Barcelona", "Hotel Barcelona", "imagen2.jpg"),
            new Alojamiento(3, "Valencia", "Hotel Valencia", "imagen3.jpg")
        );
        alojamientoRepository.saveAll(hoteles);
    }
}