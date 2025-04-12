package es.grupo9.practica1.controller;

import java.util.List;
import java.util.Set;

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

import es.grupo9.practica1.DTOs.HousingDTO;
import es.grupo9.practica1.DTOs.TagDTO;
import es.grupo9.practica1.service.HousingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/api/houses")
@Tag(name = "Housing Management", description = "APIs for managing houses and their tags")
public class HousingRestController {

    @Autowired
    HousingService housingService;


    @Operation(summary = "Get all houses", description = "Returns a list of all houses")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list of houses"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    })
    @SecurityRequirement(name = "JWT")
    @GetMapping
    public ResponseEntity<List<HousingDTO>> getAllhouses() {
        List<HousingDTO> houses = housingService.getAllHouses();
        return ResponseEntity.ok(houses);
    }


    @Operation(summary = "Get house by ID(Code)", description = "Returns a house by its ID in our api the ID of the houses is referred to as code")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved house"),
        @ApiResponse(responseCode = "404", description = "House not found"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    })
    @SecurityRequirement(name = "JWT") 
    @GetMapping("/{id}")
    public ResponseEntity<HousingDTO> getHouseById(
        @Parameter(description = "ID(Code) of the house", example = "1", required = true)
        @PathVariable Integer id) {
        HousingDTO house = housingService.getHouseById(id);
        return ResponseEntity.ok(house);
    }


    @Operation(summary = "Create a new house", description = "Creates a new house with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "House created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    })
    @SecurityRequirement(name = "JWT")
    @PostMapping
    public ResponseEntity<HousingDTO> createHouse(
        @Parameter(description = "House details needed to create the house", required = true)
        @RequestBody HousingDTO house) {
        HousingDTO createdHouse = housingService.createHouse(house);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHouse);
    }



    @Operation(summary = "Update a house", description = "Updates an existing house with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "House updated successfully"),
        @ApiResponse(responseCode = "404", description = "House not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    })
    @SecurityRequirement(name = "JWT")
    @PutMapping("/{id}")
    public ResponseEntity<HousingDTO> updateHouse(
        @Parameter(description = "ID(code) of the house to update", example = "1", required = true)
        @PathVariable int id,
        @Parameter(description = "Updated house details", required = true)
        @RequestBody HousingDTO house) {
        HousingDTO updatedHouse = housingService.updateHouse(id, house);
        return ResponseEntity.ok(updatedHouse);
    }


    @Operation(summary = "Delete a house", description = "Deletes a house by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "House deleted successfully"),
        @ApiResponse(responseCode = "404", description = "House not found"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    })
    @SecurityRequirement(name = "JWT")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHouse(
        @Parameter(description = "ID of the house to delete", example = "1", required = true)
        @PathVariable int id) {
        housingService.deleteHouse(id);
        return ResponseEntity.noContent().build();
    }
    
    @Operation(summary = "Get tags by house ID(code)", description = "Returns a set of tags associated with a house by its ID(code)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved tags"),
        @ApiResponse(responseCode = "404", description = "House not found"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    })
    @SecurityRequirement(name = "JWT")
    @GetMapping("/{id}/tags")
    public ResponseEntity<Set<TagDTO>> getTagsById(
        @Parameter(description = "ID of the house", example = "1", required = true)
        @PathVariable int id) {
        Set<TagDTO> tags = housingService.getTagsById(id);
        return ResponseEntity.ok(tags);
    }

    
}
