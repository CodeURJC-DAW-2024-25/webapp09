package es.grupo9.practica1.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.grupo9.practica1.DTOs.HousingDTO;
import es.grupo9.practica1.DTOs.RegisteredUserDTO;
import es.grupo9.practica1.DTOs.ReservationDTO;
import es.grupo9.practica1.DTOs.ReviewDTO;
import es.grupo9.practica1.DTOs.UserDTO;
import es.grupo9.practica1.entities.Tag;
import es.grupo9.practica1.entities.User;

import es.grupo9.practica1.repository.TagRepository;
import es.grupo9.practica1.security.jwt.AuthenticationRequest;
import es.grupo9.practica1.security.jwt.JwtUtil;
import es.grupo9.practica1.service.HousingService;
import es.grupo9.practica1.service.RepositoryUserDetailsService;
import es.grupo9.practica1.service.ReservationService;
import es.grupo9.practica1.service.ReviewService;
import es.grupo9.practica1.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


@Controller
public class MustacheController {

    @Autowired
    private UserService userService;

    @Autowired
    private HousingService housingService;


    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private AuthenticationManager authenticationManager;



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

        var filteredReservations = reservationService.getReservationsByOwner(user);

        model.addAttribute("reservations", filteredReservations);

        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute User updatedUser) {





        RegisteredUserDTO registeredUserDTO = new RegisteredUserDTO(updatedUser);
        userService.updateUser(updatedUser.getDni(), registeredUserDTO);

        return "index"; // Redirect to the index page
    }





    @GetMapping("/register")
    public String register(Model model) {
        return "registro";
    }

    @GetMapping("/room")
    public String room(Model model) {
        
        Pageable pageable = PageRequest.of(0, 6);

        var houses = housingService.getPaginatedHousing(pageable);

        model.addAttribute("houses", houses);
        model.addAttribute("botonajax", true);
        return "room";
    }

    @GetMapping("/room/{code}")
    public String roomDetails(@PathVariable Integer code, Model model) {


            model.addAttribute("house", housingService.findByCode(code));

            var filteredReviews = reviewService.getReviewsByHouse(code);
            model.addAttribute("comments", filteredReviews);

            return "roomDetails"; // The HTML page for room details

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

        Pageable pagination = PageRequest.of(0, 3);

        Page<HousingDTO> filteredHouses = housingService.findByAceptedFalse(pagination);
        Page<ReservationDTO> filteredReservations = reservationService.findByValoratedFalse(pagination);

        model.addAttribute("reservations", filteredReservations.getContent());
        model.addAttribute("houses", filteredHouses.getContent());

        return "admin";
    }

    @PostMapping("/v1/api/users/forms")
    public String addUser(@ModelAttribute User user, Model model) {
        RegisteredUserDTO registeredUserDTO = new RegisteredUserDTO(user);
        userService.createUser(registeredUserDTO);
        return "login";
    }

    @PostMapping("/v1/api/reservations/forms")
    public String addReservation(Model model, @RequestParam("houseId") Integer houseId,
            @RequestParam("checkIn") String checkInStr, @RequestParam("checkOut") String checkOutStr,@RequestParam("houseName") String houseName) {

        User user = (User) model.getAttribute("user");
        Date checkIn = Date.valueOf(LocalDate.parse(checkInStr));
        Date checkOut = Date.valueOf(LocalDate.parse(checkOutStr));


        // Create and save the reservation

        ReservationDTO reservationDTO = new ReservationDTO(checkIn, checkOut, false, user.getDni(), houseId, houseName);
        reservationService.createReservation(reservationDTO);

        return "redirect:/profile"; // Redirect to profile after reservation
    }

    @PostMapping("/v1/api/houses/forms")
    public String addHotel(@RequestParam("location") String location,
            @RequestParam("name") String name,
            @RequestParam("image") MultipartFile imageFile,
            @RequestParam("stars") Integer stars,
            @RequestParam("price") Integer price,
            @RequestParam("description") String description,
            @RequestParam(value = "tags", required = false) String tags,
            Model model) throws IOException {

            // Transform the image to byte
            String stringImageBytes = imageFile.toString();
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

            HousingDTO houseDTO = new HousingDTO(location, name,imageFile, price,description, stars, false,tagSet); 
            housingService.createHouse(houseDTO);
            return "redirect:/room"; // Redirect to rooms


        
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
                List<HousingDTO> filteredHouses = housingService.findHousesByTagsAndStars(tagNames, stars);
        
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


    


    @PostMapping("/v1/api/reviews/forms")
    public String addComment(@RequestParam("comment") String comment, @RequestParam("rating") Integer rating,@RequestParam("houseId") Integer houseId ,Model model) {
        User user = (User) model.getAttribute("user");
        



        ReviewDTO reviewDTO = new ReviewDTO(rating, comment, houseId, user.getDni());
        reviewService.createReview(reviewDTO);
        
        return "index";
    }
    

    @Autowired
    private RepositoryUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/v1/api/login/forms")
    public String userlogin(@RequestParam String email, @RequestParam String password, HttpServletResponse response) throws Exception {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(email, password);
        authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                                                authenticationRequest.getPassword()));




        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        Cookie jwtCookie = new Cookie("JWT", jwt);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);
        Cookie loggedCookie = new Cookie("justLoggedIn", "true");
        loggedCookie.setPath("/");
        response.addCookie(loggedCookie);
        
        // 5. Redirect to dashboard
        return "index";

    }
}
