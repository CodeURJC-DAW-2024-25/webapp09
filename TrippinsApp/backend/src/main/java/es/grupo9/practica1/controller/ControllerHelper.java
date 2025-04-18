package es.grupo9.practica1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import es.grupo9.practica1.entities.User;
import es.grupo9.practica1.repository.UserRepository;
import es.grupo9.practica1.security.jwt.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerHelper {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    UserDetailsService userDetailsService;

    @ModelAttribute
    public void addLoggedInUser(Model model,HttpServletRequest request) {
        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        


        if (authentication != null && authentication.isAuthenticated()
                && !authentication.getPrincipal().equals("anonymousUser")) {
            // Add the user's name to the model
            addUserToModel(authentication.getName(), model);
        }
        else{
            String jwt = getJwtFromCookie(request);
            if (jwt != null && jwtUtil.validateToken(jwt, userDetailsService.loadUserByUsername(authentication.getName()))) {
                
                addUserToModel(jwtUtil.extractUsername(jwt), model);
                
            }
        }
    }


    private String getJwtFromCookie(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("JWT".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private void addUserToModel(String email, Model model) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
            
        if (user.getAdmin()) {
            model.addAttribute("admin", true);
        }
        
        model.addAttribute("username", user.getName());
        model.addAttribute("user", user);
    }

}
