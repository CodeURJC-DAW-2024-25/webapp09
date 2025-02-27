package es.grupo9.practica1;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MustacheController {

    @Autowired
    private UserService userService;

    @Autowired
    private HousingService housingService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/")
    public String def(Model model){
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping("/booking")
    public String booking(Model model) {
        return "booking";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "log in";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "index";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        return "profile";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "registro";
    }

    @GetMapping("/room")
    public String room(Model model) {
        List<Housing> houseList = housingService.getAllHousings();
        model.addAttribute("houses", houseList);
        return "room";
    }

    @GetMapping("/testimonial")
    public String testimonial(Model model) {
        return "testimonial";
    }

    @GetMapping("/newhotel")
    public String newhotel(Model model) {
        return "newhotel";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user, Model model){
        userService.addUser(user.getDni(), user.getName(), user.getNumber(), user.getPassword(), user.getEmail());
        return "index";
    }   
    
    @PostMapping("/addHotel")
    public String addHotel(@ModelAttribute Housing housing, Model model){
        // Convertir la imagen a byte[] si es de tipo Blob
        byte[] imageBytes = null;
        try {
            if (housing.getImage() != null) {
                imageBytes = housing.getImage().getBytes(1, (int) housing.getImage().length());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Llamar al servicio para agregar el hotel
        housingService.addHotel(housing.getLocation(), housing.getName(), imageBytes, housing.getStars(), housing.getPrice(), housing.getDescription());

        return "index";
    }

    @PostMapping("/login")
    public String userlogin(String email, String password, Model model) {
        return "index"; 
    }
}
