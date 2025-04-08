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

import es.grupo9.practica1.DTOs.RegisteredUserDTO;
import es.grupo9.practica1.DTOs.UserDTO;

import es.grupo9.practica1.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/api/users")
@Tag(name = "User Management", description = "APIs for managing users")
public class UserRestController {


    @Autowired
    private UserService userService;


    @Operation(summary = "Get all users", description = "Returns a list of all users")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list of users"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    })
    @SecurityRequirement(name = "JWT")
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


    @Operation(summary = "Get user by ID", description = "Returns a user by their ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    })
    @SecurityRequirement(name = "JWT")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@Parameter(description = "Dni of the user", example = "11223344C", required = true)
    @PathVariable String id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }


    @Operation(summary = "Create a new user", description = "Creates a new user with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    })
    @SecurityRequirement(name = "JWT")
    @PostMapping
    public ResponseEntity<UserDTO> createUser(
        @Parameter(description = "User details for creation", required = true)
        @RequestBody RegisteredUserDTO user) {
        UserDTO createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    @Operation(summary = "Update a user", description = "Updates an existing user with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User updated successfully"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    })
    @SecurityRequirement(name = "JWT")    
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(
        @Parameter(description = "Dni of the user to update", example = "11223344C", required = true)
        @PathVariable String id, 
        @Parameter(description = "Updated user details", required = true)
        @RequestBody RegisteredUserDTO user) {
        UserDTO updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Delete a user", description = "Deletes a user by their ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "User deleted successfully"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    })
    @SecurityRequirement(name = "JWT")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
        @Parameter(description = "Dni of the user to delete", example = "11223344C", required = true)
        @PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    
}
