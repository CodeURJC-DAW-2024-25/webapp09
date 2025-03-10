package es.grupo9.practica1.controller;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.grupo9.practica1.entities.Housing;
import es.grupo9.practica1.entities.Tag;
import es.grupo9.practica1.entities.User;
import es.grupo9.practica1.repository.HousingRepository;
import es.grupo9.practica1.repository.ReservationRepository;
import es.grupo9.practica1.repository.ReviewRepository;
import es.grupo9.practica1.repository.TagRepository;
import es.grupo9.practica1.repository.UserRepository;
import es.grupo9.practica1.service.HousingService;
import es.grupo9.practica1.service.ReservationService;
import es.grupo9.practica1.service.ReviewService;
import es.grupo9.practica1.service.UserService;

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

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private TagRepository tagRepository;

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

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute User updatedUser) {
        // Get the currently logged-in user's email (or username)
        String email = updatedUser.getEmail();

        // Fetch the existing user from the database
        Optional<User> existingUser = userRepository.findByEmail(email);

        User finalUser = existingUser.get();

        // Update the user's data
        finalUser.setName(updatedUser.getName());
        finalUser.setEmail(updatedUser.getEmail());
        finalUser.setNumber(updatedUser.getNumber());
        finalUser.setPassword(updatedUser.getPassword()); 

        // Save the updated user to the database
        userRepository.save(finalUser);

        return "index"; // Redirect to the index page
    }





    @GetMapping("/register")
    public String register(Model model) {
        return "registro";
    }

    @GetMapping("/room")
    public String room(Model model) {
        
        Pageable pageable = PageRequest.of(0, 6);
        var houses = housingRepository.findAll(pageable).getContent();

        model.addAttribute("houses", houses);
        model.addAttribute("botonajax", true);
        return "room";
    }

    @GetMapping("/room/{code}")
    public String roomDetails(@PathVariable Integer code, Model model) {
        Optional<Housing> optionalHousing = housingRepository.findByCode(code);
        if (optionalHousing.isPresent()) {
            Housing house = optionalHousing.get();
            model.addAttribute("house", house);
            var allReviews = reviewRepository.findAll();

            var filteredReviews = allReviews.stream()
                    .filter(review -> review.getHotel().getCode() == code)// igual da error que cod ees int no Integer
                    .limit(3)
                    .collect(Collectors.toList());
            model.addAttribute("comments", filteredReviews);

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
            @RequestParam(value = "tags", required = false) String tags,
            Model model) {

        try {
            // Transform the image to byte
            byte[] imageBytes = imageFile.getBytes();
            
            // Add the hotel via service

            Set<Tag> tagSet = new HashSet<>();
            if (tags != null && !tags.isEmpty()){
                String[] tagNames = tags.split(",");
                for (String tagName : tagNames) {
                    final String tagName1 = tagName.trim().toLowerCase(); // Clean up tag names
                    Tag tag = tagRepository.findOrCreate(tagName1);
                    tagSet.add(tag);
                }

            } 

            housingService.addHotel(location, name, imageBytes, stars, price, description,tagSet);
            return "redirect:/room"; // Redirect to rooms
        } catch (IOException e) {
            model.addAttribute("error", "Error al cargar la imagen.");
            e.printStackTrace();
            return "newhotel"; // Return to the form page in case of error
        }

        
    }

    @PostMapping("/search")
    public String searchHouses(@RequestParam(value = "tags", required = false) String tags,
        @RequestParam(value = "stars", required = false) Integer stars,Model model) {
            try {
                // Split the tags into a list (if provided)
                Set<String> tagNames = new HashSet<>();
                if (tags != null && !tags.isEmpty()) {
                    String[] tagArray = tags.split(",");
                    for (String tag : tagArray) {
                        tagNames.add(tag.trim().toLowerCase());
                    }
                }
        
                // Query the database for houses that match the criteria
                List<Housing> filteredHouses = housingService.findHousesByTagsAndStars(tagNames, stars);
        
                // Add the filtered houses to the model
                model.addAttribute("houses", filteredHouses);
                //Not adding the button for the query
                model.addAttribute("botonajax", false);
                
                return "room"; 
            } catch (Exception e) {
                
                e.printStackTrace();
                return "room"; 
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
