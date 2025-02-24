package es.grupo9.practica1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MustacheController {

    @GetMapping("/index")
    public String index(Model model) {

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

        return "login";
    }

    // Profile page
    @GetMapping("/profile")
    public String profile(Model model) {

        return "profile";
    }

    // Registration page
    @GetMapping("/registro")
    public String registro(Model model) {

        return "registro";
    }

    // Room page
    @GetMapping("/room")
    public String room(Model model) {

        return "room";
    }

    // Testimonial page
    @GetMapping("/testimonial")
    public String testimonial(Model model) {

        return "testimonial";
    }

}
