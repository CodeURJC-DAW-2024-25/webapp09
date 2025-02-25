package es.grupo9.practica1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class HousingService {

    @Autowired
    private HousingRepository housingRepository;

    public Housing addHotel(int code, String location, String name, String image) {
        Housing newHotel = new Housing(code, location, name, image);
        return housingRepository.save(newHotel);
    }

    public void initializeHotels() {
        List<Housing> hoteles = Arrays.asList(
            new Housing(1, "Madrid", "Hotel Madrid", "imagen1.jpg"),
            new Housing(2, "Barcelona", "Hotel Barcelona", "imagen2.jpg"),
            new Housing(3, "Valencia", "Hotel Valencia", "imagen3.jpg")
        );
        housingRepository.saveAll(hoteles);
    }
}