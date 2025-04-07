package es.grupo9.practica1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import es.grupo9.practica1.DTOs.ReviewDTO;
import es.grupo9.practica1.service.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/v1/api/reviews")
@Tag(name = "Review Management", description = "APIs for managing reviews")
public class ReviewRestController {

    @Autowired
    private ReviewService reviewService;


    @Operation(summary = "Get all reviews", description = "Returns a list of all reviews")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list of reviews"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    })
    @SecurityRequirement(name = "JWT")
    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        List<ReviewDTO> reviewDTOs = reviewService.getAllReviews();
        return ResponseEntity.ok(reviewDTOs);
    }


    @Operation(summary = "Get a specific review by ID", description = "Returns a review by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved review"),
        @ApiResponse(responseCode = "404", description = "Review not found"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    })
    @SecurityRequirement(name = "JWT")
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(
        @Parameter(description = "ID of the review", example = "1", required = true)
        @PathVariable Integer id) {
        ReviewDTO review = reviewService.getReviewById(id);
        return ResponseEntity.ok(review);
    }


    @Operation(summary = "Create a new review", description = "Creates a new review with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Review created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    })
    @SecurityRequirement(name = "JWT")
    @PostMapping
    public ResponseEntity<ReviewDTO> createReview(
        @Parameter(description = "Review details needed for the creation", required = true)
        @RequestBody ReviewDTO review) {
        ReviewDTO createdReview = reviewService.createReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }


    @Operation(summary = "Update a review", description = "Updates an existing review with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Review updated successfully"),
        @ApiResponse(responseCode = "404", description = "Review not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    })
    @SecurityRequirement(name = "JWT")
    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> updateReview(
        @Parameter(description = "ID of the review to update", example = "1", required = true)
        @PathVariable Integer id,
        @Parameter(description = "Updated review details", required = true)
        @RequestBody ReviewDTO review) {
        ReviewDTO updatedReview = reviewService.updateReview(id, review);
        return ResponseEntity.ok(updatedReview);
    }


    @Operation(summary = "Delete a review", description = "Deletes a review by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Review deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Review not found"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    })
    @SecurityRequirement(name = "JWT") 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(
        @Parameter(description = "ID of the review to delete", example = "1", required = true)
        @PathVariable Integer id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

}
