package es.grupo9.practica1.controller;

import es.grupo9.practica1.DTOs.ReviewDTO;
import es.grupo9.practica1.service.ReviewService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.security.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/reviews")
@Tag(name = "Review API", description = "Endpoint collection for hotel reviews management")
public class ReviewApiController {

    private final ReviewService reviewService;

    // Inyección de dependencias por constructor
    public ReviewApiController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Fetch all reviews", description = "Retrieves complete list of registered reviews")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Review list retrieved"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<List<ReviewDTO>> fetchAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }


    @GetMapping("/{id}")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Find review by ID", description = "Locates specific review using unique identifier")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Review found"),
        @ApiResponse(responseCode = "404", description = "Review not found"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<ReviewDTO> findReviewById(
        @Parameter(description = "Unique review identifier", example = "1", required = true)
        @PathVariable Integer id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }


    @PostMapping
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Register new review", description = "Creates and stores new review record")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Review created"),
        @ApiResponse(responseCode = "400", description = "Invalid data"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<ReviewDTO> registerReview(
        @Parameter(description = "Complete review information", required = true)
        @RequestBody ReviewDTO reviewData) {
        ReviewDTO newReview = reviewService.createReview(reviewData);
        return ResponseEntity.status(HttpStatus.CREATED).body(newReview);
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Modify review", description = "Updates existing review information")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Review updated"),
        @ApiResponse(responseCode = "404", description = "Review not found"),
        @ApiResponse(responseCode = "400", description = "Invalid data"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<ReviewDTO> modifyReview(
        @Parameter(description = "Review identifier to update", example = "1", required = true)
        @PathVariable Integer id,
        @Parameter(description = "Updated review content", required = true)
        @RequestBody ReviewDTO reviewData) {
        return ResponseEntity.ok(reviewService.updateReview(id, reviewData));
    }


    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Remove review", description = "Permanently deletes review record")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Review deleted"),
        @ApiResponse(responseCode = "404", description = "Review not found"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<Void> removeReview(
        @Parameter(description = "Review identifier to delete", example = "1", required = true)
        @PathVariable Integer id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}