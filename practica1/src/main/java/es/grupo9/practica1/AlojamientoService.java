package es.grupo9.practica1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AlojamientoService {

    @Autowired
    private AlojamientoRepository alojamientoRepository;

    public Alojamiento addHotel(int codigo, String ubicacion, String nombre, String imagen) {
        Alojamiento newHotel = new Alojamiento(codigo, ubicacion, nombre, imagen);
        return alojamientoRepository.save(newHotel);
    }

    public void initializeHotels() {
        List<Alojamiento> hoteles = Arrays.asList(
            new Alojamiento(1, "Madrid", "Hotel Madrid", "imagen1.jpg"),
            new Alojamiento(2, "Barcelona", "Hotel Barcelona", "imagen2.jpg"),
            new Alojamiento(3, "Valencia", "Hotel Valencia", "imagen3.jpg")
        );
        alojamientoRepository.saveAll(hoteles);
    }
}