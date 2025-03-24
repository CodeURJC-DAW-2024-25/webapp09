package es.grupo9.practica1.controller;

import es.grupo9.practica1.DTOs.RegisteredUserDTO;
import es.grupo9.practica1.DTOs.UserDTO;
import es.grupo9.practica1.service.UserService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.security.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "Endpoint collection for user management")
public class UserApiController {

    private final UserService userService;

    // Inyección de dependencias mediante constructor
    public UserApiController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Retrieve all users", description = "Fetches complete list of registered users")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User list retrieved successfully"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<List<UserDTO>> retrieveAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{dni}")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Find user by DNI", description = "Locates specific user using their national ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User found"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<UserDTO> findUserByDni(
        @Parameter(description = "User's national ID", example = "11223344C", required = true)
        @PathVariable String dni) {
        return ResponseEntity.ok(userService.getUserById(dni));
    }


    @PostMapping
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Register new user", description = "Creates and stores new user record")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "User created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<UserDTO> registerUser(
        @Parameter(description = "Complete user information", required = true)
        @RequestBody RegisteredUserDTO userData) {
        UserDTO newUser = userService.createUser(userData);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }


    @PutMapping("/{dni}")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Update user", description = "Modifies existing user information")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User updated successfully"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<UserDTO> updateUser(
        @Parameter(description = "User's national ID", example = "11223344C", required = true)
        @PathVariable String dni,
        @Parameter(description = "Updated user information", required = true)
        @RequestBody RegisteredUserDTO userData) {
        return ResponseEntity.ok(userService.updateUser(dni, userData));
    }

 
    @DeleteMapping("/{dni}")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Remove user", description = "Permanently deletes user record")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "User deleted successfully"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<Void> removeUser(
        @Parameter(description = "User's national ID", example = "11223344C", required = true)
        @PathVariable String dni) {
        userService.deleteUser(dni);
        return ResponseEntity.noContent().build();
    }
}