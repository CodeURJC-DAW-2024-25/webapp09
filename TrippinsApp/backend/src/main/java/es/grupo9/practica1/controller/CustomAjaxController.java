package es.grupo9.practica1.controller;


import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.grupo9.practica1.DTOs.HousingDTO;
import es.grupo9.practica1.DTOs.ReviewDTO;
import es.grupo9.practica1.entities.Housing;

import es.grupo9.practica1.entities.Review;

import es.grupo9.practica1.service.HousingService;
import es.grupo9.practica1.service.ReservationService;
import es.grupo9.practica1.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.data.domain.Pageable;

@RestController
@Tag(name = "Custom AJAX Endpoints", description = "APIs for pagination, loading elements, and managing reservations and houses via AJAX")
public class CustomAjaxController {
    

    



    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReviewService reviewService;

    @Autowired 
    private HousingService housingService;


    @Operation(
        summary = "Get paginated houses", 
        description = "Returns a paginated list of accepted houses."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Successfully retrieved paginated houses",
            content = @Content(
                schema = @Schema(implementation = Housing.class)
            )
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Internal server error",
            content = @Content(
                examples = @ExampleObject(
                    value = "{\"error\": \"Internal Server Error\", \"message\": \"Error fetching houses\"}"
                )
            )
        )
    })
    @PostMapping("/api/rooms/extra") 
    public Page<HousingDTO> getHouses(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Pagination parameters",
                required = true,
                content = @Content(
                    schema = @Schema(implementation = Map.class),
                    examples = @ExampleObject(
                        value = "{\"page\": 0, \"size\": 6}"
                    )
                )
            )  
        @RequestBody Map<String, Integer> request) {
        try {
            // Extract page and size from the request body
            int page = request.getOrDefault("page", 0); // Default to page 0 if not provided
            int size = request.getOrDefault("size", 6); // Default to size 6 if not provided

            // Log the received parameters
            System.out.println("Received request - page: " + page + ", size: " + size);

            // Fetch the houses using pagination
            Pageable pageable = PageRequest.of(page, size);
            
            Page<HousingDTO> houses = housingService.findByAceptedTrue(pageable);
            // Log the number of houses fetched
            System.out.println("Fetched " + houses.getNumberOfElements() + " houses");

            return houses;
        } catch (Exception e) {
            // Log the error
            System.err.println("Error fetching houses: " + e.getMessage());
            throw e; // Re-throw the exception to return a 500 error
        }
    }


    
    @Operation(
        summary = "Get paginated comments for a house", 
        description = "Returns a paginated list of comments for a specific house."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Successfully retrieved paginated comments",
            content = @Content(
                schema = @Schema(implementation = Review.class)
            )
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Internal server error",
            content = @Content(
                examples = @ExampleObject(
                    value = "{\"error\": \"Internal Server Error\", \"message\": \"Error fetching comments\"}"
                )
            )
        )
    })
    @PostMapping("/api/rooms/{id}/comments/extra")
    public Page<ReviewDTO> getComments(
        @Parameter(description = "ID of the house", example = "1", required = true)
        @PathVariable Integer id,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Pagination parameters",
                required = true,
                content = @Content(
                    schema = @Schema(implementation = Map.class),
                    examples = @ExampleObject(
                        value = "{\"page\": 0, \"size\": 6}"
                    )
                )
            ) 
        @RequestBody Map<String, Integer> request) {
        try {
            // Extract  page, and size from the request body
            Integer hotelId = id; 
            int page = request.getOrDefault("page", 0); // Default to page 0 if not provided
            int size = request.getOrDefault("size", 3); // Default to size 3 if not provided

            
            // Log the received parameters
            System.out.println("Received request - hotelId: " + hotelId + ", page: " + page + ", size: " + size);

            // Fetch comments using pagination


            Pageable pageable = PageRequest.of(page, size);
            Page<ReviewDTO> comments = reviewService.findByHotel(hotelId, pageable);

            // Log the number of comments fetched
            System.out.println("Fetched " + comments.getNumberOfElements() + " comments");

            return comments;
        } catch (Exception e) {
            // Log the error
            System.err.println("Error fetching comments: " + e.getMessage());
            throw e; // Re-throw the exception to return a 500 error
        }
    }



    @Operation(
        summary = "Get paginated unaccepted houses for admin", 
        description = "Returns a paginated list of unaccepted houses for admin review."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Successfully retrieved paginated unaccepted houses",
            content = @Content(
                schema = @Schema(implementation = Page.class)
            )
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Internal server error",
            content = @Content(
                examples = @ExampleObject(
                    value = "{\"error\": \"Internal Server Error\", \"message\": \"Error fetching houses\"}"
                )
            )
        )
    })
    @SecurityRequirement(name = "JWT")
    @PostMapping("/api/admin/houses")
    public Page<HousingDTO> getAdminHouses(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Pagination parameters",
                required = true,
                content = @Content(
                    schema = @Schema(implementation = Map.class),
                    examples = @ExampleObject(
                        value = "{\"page\": 0, \"size\": 3}"
                    )
                )
            )
        @RequestBody Map<String, Integer> request) {
        try {
            // Extract page and size from the request body
            int page = request.getOrDefault("page", 0); // Default to page 0 if not provided
            int size = request.getOrDefault("size", 3); // Default to size 3 if not provided

            // Log the received parameters
            System.out.println("Received request - page: " + page + ", size: " + size);

            // Fetch the houses using pagination
            Pageable pageable = PageRequest.of(page, size);

            
            Page<HousingDTO> houses = housingService.findByAceptedFalse(pageable);

            return houses;
        } catch (Exception e) {
            // Log the error
            System.err.println("Error fetching houses: " + e.getMessage());
            throw e; // Re-throw the exception to return a 500 error
        }
    }


    // Accept House: Sets the "acepted" field to true
    
    @Operation(
        summary = "Accept a house", 
        description = "Accepts a house by setting its 'acepted' field to true. "
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "House accepted successfully"
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "House not found"
        ),
        @ApiResponse(
            responseCode = "403", 
            description = "Forbidden - Access denied (requires ADMIN role)"
        ),
        @ApiResponse(
            responseCode = "401", 
            description = "Unauthorized - JWT token missing or invalid"
        )
    })
    @SecurityRequirement(name = "JWT")
    @PutMapping("/api/admin/houses/decision/{houseId}")
    public void acceptHouse(
        @Parameter(description = "ID(code) of the house", example = "1", required = true)
        @PathVariable Integer houseId) {

        housingService.acceptHouse( houseId);

    }

    // Deny House: Deletes the house from the database

    @Operation(
        summary = "Deny a house", 
        description = "Denies a house by deleting it from the database."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204", 
            description = "House denied and removed successfully"
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "House not found"
        ),
        @ApiResponse(
            responseCode = "403", 
            description = "Forbidden - Access denied (requires ADMIN role)"
        ),
        @ApiResponse(
            responseCode = "401", 
            description = "Unauthorized - JWT token missing or invalid"
        )
    })
    @SecurityRequirement(name = "JWT")
    @DeleteMapping("/api/admin/houses/decision/{houseId}")
    public void denyHouse(
        @Parameter(description = "ID(code) of the house", example = "1", required = true)
        @PathVariable Integer houseId) {
        housingService.deleteHouse(houseId);
    }

    // Accept reservation: Sets the "valorated" field to true
    @Operation(
        summary = "Accept a reservation", 
        description = "Accepts a reservation by setting its 'valorated' field to true. "
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Reservation accepted successfully"
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Reservation not found"
        ),
        @ApiResponse(
            responseCode = "403", 
            description = "Forbidden - Access denied (requires ADMIN role)"
        ),
        @ApiResponse(
            responseCode = "401", 
            description = "Unauthorized - JWT token missing or invalid"
        )
    })
    @SecurityRequirement(name = "JWT")
    @PutMapping("/api/admin/reservations/decision/{reservationId}")
    public void acceptReservation(
        @Parameter(description = "ID of the reservation", example = "1", required = true)
        @PathVariable Integer reservationId) {

        reservationService.acceptReservation(reservationId);

    }

    @Operation(
        summary = "Deny a reservation", 
        description = "Denies a reservation by deleting it from the database. **Requires ADMIN role.**"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204", 
            description = "Reservation denied and removed successfully"
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Reservation not found"
        ),
        @ApiResponse(
            responseCode = "403", 
            description = "Forbidden - Access denied (requires ADMIN role)"
        ),
        @ApiResponse(
            responseCode = "401", 
            description = "Unauthorized - JWT token missing or invalid"
        )
    })
    @SecurityRequirement(name = "JWT")
    //Deny reservation: just deletes from the database
    @DeleteMapping("/api/admin/reservations/decision/{reservationId}")
    public void denyReservation(
        @Parameter(description = "ID of the reservation", example = "1", required = true)
        @PathVariable Integer reservationId) {
        reservationService.deleteReservation(reservationId);
    }

}