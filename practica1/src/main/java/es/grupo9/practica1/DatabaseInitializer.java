package es.grupo9.practica1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner{




    @Autowired
    private HousingService housingService;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        housingService.initializeHotels();
        userService.initializeUsers();
    }

}
