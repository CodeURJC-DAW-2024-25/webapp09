package es.grupo9.practica1;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Pageable;

@Controller
public class MustacheController {

    @Autowired
    private UserService userService;

    @Autowired
    private HousingService housingService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HousingRepository housingRepository;

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
        //List<Housing> houseList = housingService.getAllHousings();
        Pageable pageable = PageRequest.of(0, 6);
        var houses = housingRepository.findAll(pageable).getContent();
        //model.addAttribute("houses", houseList);
        model.addAttribute("houses", houses);
        return "room";
    }



    @GetMapping("/testimonial")
    public String testimonial(Model model) {
        return "testimonial";
    }

    @GetMapping("/newHotel")
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
        return "login";
    }   
    
    @PostMapping("/addHotel")
    public String addHotel(@RequestParam("location") String location,
    @RequestParam("name") String name,
    @RequestParam("image") MultipartFile imageFile,
    @RequestParam("stars") Integer stars,
    @RequestParam("price") Integer price,
    @RequestParam("description") String description,
        
        Model model) {
    
        try {
            // Convertir la imagen a byte[]
            byte[] imageBytes = imageFile.getBytes();
    
            // Llamar al servicio para agregar el hotel
            housingService.addHotel(location, name, imageBytes, stars, price, description);
    
            return "redirect:/room"; // Redirigir a la página de habitaciones
        } catch (IOException e) {
            model.addAttribute("error", "Error al cargar la imagen.");
            e.printStackTrace();
            return "newhotel"; // Volver al formulario en caso de error
        }
    }

    @PostMapping("/login")
    public String userlogin(String email, String password, Model model) {
        return "index"; 
    }
}
