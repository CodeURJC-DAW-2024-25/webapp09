package es.grupo9.practica1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.grupo9.practica1.DTOs.HousingDTO;
import es.grupo9.practica1.service.HousingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/v1/api/query")
@Tag(name = "Advanced Housing Search", description = "API for advanced housing search operations")
public class AdvancedSearchRestController {


    @Autowired
    private HousingService housingService;
    @Operation(
        summary = "Search houses by tags and stars",
        description = "Returns a list of houses filtered by optional tags and minimum star rating",
        parameters = {
            @Parameter(
                name = "tags",
                description = "Comma-separated list of tags to filter by (e.g. 'beach,pool,wifi')",
                example = "beach,pool",
                in = ParameterIn.QUERY,
                schema = @Schema(type = "string"))
            ,
            @Parameter(
                name = "stars",
                description = "Minimum star rating to filter by (1-5)",
                example = "4",
                in = ParameterIn.QUERY,
                schema = @Schema(type = "integer", minimum = "1", maximum = "5"))
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successful operation",
                content = @Content(
                    mediaType = "application/json",
                    array = @io.swagger.v3.oas.annotations.media.ArraySchema(schema = @Schema(implementation = HousingDTO.class)))),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
        })
    @GetMapping
    public ResponseEntity<?> searchHouses(
        @RequestParam(value = "tags", required = false) String tags,
        @RequestParam(value = "stars", required = false) Integer stars) {
        
        try {
            // Process tags
            Set<String> tagNames = tags != null && !tags.isEmpty() 
                ? Arrays.stream(tags.split(","))
                      .map(String::trim)
                      .map(String::toLowerCase)
                      .collect(Collectors.toSet())
                : Collections.emptySet();

            // Query the database
            List<HousingDTO> filteredHouses = housingService.findHousesByTagsAndStars(tagNames, stars);

            // Return as JSON response
            return ResponseEntity.ok(filteredHouses);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                    "error", "Search failed",
                    "message", e.getMessage()
                ));
        }
    }
    
    
}
