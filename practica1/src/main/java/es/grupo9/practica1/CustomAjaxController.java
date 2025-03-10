package es.grupo9.practica1;


import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

@RestController
public class CustomAjaxController {

    @Autowired
    private HousingRepository housingRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @PostMapping("/roomHouses")
    public Page<Housing> getHouses(@RequestBody Map<String, Integer> request) {
        try {
            // Extract page and size from the request body
            int page = request.getOrDefault("page", 0); // Default to page 0 if not provided
            int size = request.getOrDefault("size", 6); // Default to size 6 if not provided

            // Log the received parameters
            System.out.println("Received request - page: " + page + ", size: " + size);

            // Fetch the houses using pagination
            Pageable pageable = PageRequest.of(page, size);
            Page<Housing> houses = housingRepository.findByAceptedTrue(pageable);

            // Log the number of houses fetched
            System.out.println("Fetched " + houses.getNumberOfElements() + " houses");

            return houses;
        } catch (Exception e) {
            // Log the error
            System.err.println("Error fetching houses: " + e.getMessage());
            throw e; // Re-throw the exception to return a 500 error
        }
    }

    @PostMapping("/loadComments")
    public Page<Review> getComments(@RequestBody Map<String, Integer> request) {
        try {
            // Extract hotel ID, page, and size from the request body
            Integer hotelId = request.getOrDefault("hotelId", 1); // Default to 0 if not provided
            int page = request.getOrDefault("page", 0); // Default to page 0 if not provided
            int size = request.getOrDefault("size", 3); // Default to size 3 if not provided

            
            // Log the received parameters
            System.out.println("Received request - hotelId: " + hotelId + ", page: " + page + ", size: " + size);

            // Fetch comments using pagination
            Optional<Housing> house = housingRepository.findByCode(hotelId);

            Pageable pageable = PageRequest.of(page, size);
            Page<Review> comments = reviewRepository.findByHotel(house.get(), pageable);

            // Log the number of comments fetched
            System.out.println("Fetched " + comments.getNumberOfElements() + " comments");

            return comments;
        } catch (Exception e) {
            // Log the error
            System.err.println("Error fetching comments: " + e.getMessage());
            throw e; // Re-throw the exception to return a 500 error
        }
    }

    @PostMapping("/adminHouses")
    public Page<Housing> getAdminHouses(@RequestBody Map<String, Integer> request) {
        try {
            // Extract page and size from the request body
            int page = request.getOrDefault("page", 0); // Default to page 0 if not provided
            int size = request.getOrDefault("size", 3); // Default to size 3 if not provided

            // Log the received parameters
            System.out.println("Received request - page: " + page + ", size: " + size);

            // Fetch the houses using pagination
            Pageable pageable = PageRequest.of(page, size);
            Page<Housing> houses = housingRepository.findByAceptedFalse(pageable);

            return houses;
        } catch (Exception e) {
            // Log the error
            System.err.println("Error fetching houses: " + e.getMessage());
            throw e; // Re-throw the exception to return a 500 error
        }
    }

    // Accept House: Sets the "acepted" field to true
    @PostMapping("/acceptHouse/{houseId}")
    public void acceptHouse(@PathVariable Integer houseId) {
        Optional<Housing> optionalHouse = housingRepository.findById(houseId);
        if (optionalHouse.isPresent()) {
            Housing house = optionalHouse.get();
            house.setAcepted(true);
            housingRepository.save(house);
            System.out.println("House with ID " + houseId + " has been accepted.");
        } else {
            System.err.println("House with ID " + houseId + " not found.");
        }
    }

    // Deny House: Deletes the house from the database
    @DeleteMapping("/denyHouse/{houseId}")
    public void denyHouse(@PathVariable Integer houseId) {
        if (housingRepository.existsById(houseId)) {
            housingRepository.deleteById(houseId);
            System.out.println("House with ID " + houseId + " has been denied and removed.");
        } else {
            System.err.println("House with ID " + houseId + " not found.");
        }
    }

    @PostMapping("/acceptReservation/{reservationId}")
    public void acceptReservation(@PathVariable Integer reservationId) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setValorated(true);
            reservationRepository.save(reservation);

        }
    }

    @DeleteMapping("/denyReservation/{reservationId}")
    public void denyReservation(@PathVariable Integer reservationId) {
        if (reservationRepository.existsById(reservationId)) {
            reservationRepository.deleteById(reservationId);
        }
    }

}
