package es.grupo9.practica1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;






@Controller
public class MustacheController {

    @GetMapping("/index")
    public String index(Model model) {

        return "index";
    }

    @GetMapping("/")
    public String def(Model model){

        return "index";
    }

    // About page
    @GetMapping("/about")
    public String about(Model model) {

        return "about";
    }

    // Booking page
    @GetMapping("/booking")
    public String booking(Model model) {

        return "booking";
    }

    // Contact page
    @GetMapping("/contact")
    public String contact(Model model) {

        return "contact";
    }

    // Log in page
    @GetMapping("/login")
    public String login(Model model) {

        return "log in";
    }

    // Profile page
    @GetMapping("/profile")
    public String profile(Model model) {

        return "profile";
    }

    // Registration page
    @GetMapping("/register")
    public String register(Model model) {

        return "registro";
    }

    // Room page
    @GetMapping("/room")
    public String room(Model model) {
        List<Housing> houseList = housingService.getAllHousings();
        model.addAttribute("houses", houseList);

        return "room";
    }

    // Testimonial page
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

    @Autowired
    private UserService userService;

    @Autowired
    private HousingService housingService;


    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user, Model model){
        userService.addUser(user.getDni(), user.getName(), user.getNumber(), user.getPassword(), user.getEmail());

        return "index";

    }   
    
    @PostMapping("/addHotel")
    public String addHotel(@ModelAttribute Housing housing, Model model){
        housingService.addHotel(housing.getLocation(), housing.getName(), housing.getImage(), housing.getStars(), housing.getPrice(), housing.getDescription(), housing.getAcepted());

        return "index";
    }
    

    @PostMapping("/userlogin")
    public String userlogin(String email, String Password, Model model) {
        userService.userlogin(email, Password);
        return "index"; 
    }
    
    

}
