package es.grupo9.practica1.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.grupo9.practica1.security.jwt.AuthenticationRequest;
import es.grupo9.practica1.security.jwt.AuthenticationResponse;
import es.grupo9.practica1.security.jwt.JwtUtil;
import es.grupo9.practica1.service.RepositoryUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/v1/api")
@Tag(name = "Authentication Management", description = "This APIs is the one in charge of loggin in/loggin off and authenticating users and their data")
public class AuthenticationRestController {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private RepositoryUserDetailsService userDetailsService;

        @Autowired
        private JwtUtil jwtUtil;

        @Operation(
                summary = "Log in user", 
                description = "Authenticates a user with their email and password, and returns a JWT token and their roles.")
        @ApiResponses(value = {
                @ApiResponse(
                responseCode = "200", 
                description = "Successfully authenticated",
                content = @Content(
                        schema = @Schema(implementation = AuthenticationResponse.class),
                        examples = @ExampleObject(
                        value = "{\"jwt\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...\", \"roles\": [\"ROLE_USER\"]}"
                        )
                )
                ),
                @ApiResponse(
                responseCode = "401", 
                description = "Unauthorized - Invalid credentials",
                content = @Content(
                        examples = @ExampleObject(
                        value = "{\"error\": \"Unauthorized\", \"message\": \"Bad credentials\"}"
                        )
                )
                ),
                @ApiResponse(
                responseCode = "400", 
                description = "Bad Request - Invalid input",
                content = @Content(
                        examples = @ExampleObject(
                        value = "{\"error\": \"Bad Request\", \"message\": \"Invalid input\"}"
                        )
                )
                )
        })
        @PostMapping("/login")
        public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
                        throws Exception {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                                                authenticationRequest.getPassword()));

                final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
                final String jwt = jwtUtil.generateToken(userDetails);

                List<String> roles = userDetails.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList());

                return ResponseEntity.ok(new AuthenticationResponse(jwt, roles));
        }

}
