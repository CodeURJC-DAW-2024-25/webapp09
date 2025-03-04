package es.grupo9.practica1;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

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
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private HousingRepository housingRepository;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/")
    public String def(Model model) {
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
        User user = (User) model.getAttribute("user");
        var reservations = reservationRepository.findAll();

        var filteredReservations = reservations.stream()
                .filter(reservation -> reservation.getID_cliente().getDni().equals(user.getDni())) // Filters houses per dni
                .limit(3) // Limit the result to 3
                .collect(Collectors.toList());

        model.addAttribute("reservations", filteredReservations);

        return "profile";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "registro";
    }

    @GetMapping("/room")
    public String room(Model model) {
        // Working code previous to ajax
        // List<Housing> houseList = housingService.getAllHousings();
        // model.addAttribute("houses", houseList);
        Pageable pageable = PageRequest.of(0, 6);
        var houses = housingRepository.findAll(pageable).getContent();

        model.addAttribute("houses", houses);
        return "room";
    }

    @GetMapping("/room/{code}")
    public String roomDetails(@PathVariable Integer code, Model model) {
        Optional<Housing> optionalHousing = housingRepository.findByCode(code);
        if (optionalHousing.isPresent()) {
            Housing house = optionalHousing.get();
            model.addAttribute("house", house);

            // Load comments
            // List<Review> comments = ReviewRepository.findByHotel(id);
            // model.addAttribute("comments", comments);

            return "roomDetails"; // The HTML page for room details
        }
        return "redirect:/room";
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

        var reservations = reservationRepository.findAll();
        var allHouses = housingRepository.findAll();

        var filteredHouses = allHouses.stream()
                .filter(house -> !house.getAcepted()) // Filters only unaccepted houses
                .limit(3)                      // Limit the result to 3 houses
                .collect(Collectors.toList());
        var filteredReservations = reservations.stream() //Literally the same but for reservations
                .filter(reservation -> !reservation.isValorated()) 
                .limit(3) 
                .collect(Collectors.toList());

        model.addAttribute("reservations", filteredReservations);
        model.addAttribute("houses", filteredHouses);

        return "admin";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user, Model model) {
        userService.addUser(user.getDni(), user.getName(), user.getNumber(), user.getPassword(), user.getEmail());
        return "login";
    }

    @PostMapping("/addReservation")
    public String addReservation(Model model, @RequestParam("houseId") Integer houseId,
            @RequestParam("checkIn") String checkInStr, @RequestParam("checkOut") String checkOutStr) {

        User user = (User) model.getAttribute("user");
        Date checkIn = Date.valueOf(LocalDate.parse(checkInStr));
        Date checkOut = Date.valueOf(LocalDate.parse(checkOutStr));

        // Get the house from the repository
        Optional<Housing> optionalHousing = housingRepository.findByCode(houseId);
        if (optionalHousing.isEmpty()) {
            return "redirect:/room"; // Redirect if the house doesn't exist
        }
        Housing house = optionalHousing.get();

        // Create and save the reservation

        reservationService.addReservation(user, house, checkIn, checkOut);

        return "redirect:/profile"; // Redirect to profile after reservation
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
            // Transform the image to byte
            byte[] imageBytes = imageFile.getBytes();

            // Add the hotel via service
            housingService.addHotel(location, name, imageBytes, stars, price, description);

            return "redirect:/room"; // Redirect to rooms
        } catch (IOException e) {
            model.addAttribute("error", "Error al cargar la imagen.");
            e.printStackTrace();
            return "newhotel"; // Return to the form page in case of error
        }
    }

    @PostMapping("/addComment")
    public String addComment(@RequestParam("comment") String comment, @RequestParam("rating") Integer rating,@RequestParam("houseId") Integer houseId ,Model model) {
        User user = (User) model.getAttribute("user");
        
        Optional<Housing> optionalHousing = housingRepository.findByCode(houseId);

        Housing house = optionalHousing.get();



        reviewService.addReview(rating, comment,house ,user );
        
        return "index";
    }
    





    @PostMapping("/login")
    public String userlogin(String email, String password, Model model) {
        return "index";
    }
}
