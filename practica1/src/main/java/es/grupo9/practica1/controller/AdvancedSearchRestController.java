package es.grupo9.practica1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.grupo9.practica1.DTOs.HousingDTO;
import es.grupo9.practica1.service.HousingService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/v1/api/query")
public class AdvancedSearchRestController {


    @Autowired
    private HousingService housingService;

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
