package es.grupo9.practica1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class ControllerHelper {

    @Autowired
    private UserRepository userRepository;
    
    @ModelAttribute
    public void addLoggedInUser(Model model) {
        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && !authentication.getPrincipal().equals("anonymousUser")) {
            // Add the user's name to the model
            String email = authentication.getName();
            
            User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

            
            model.addAttribute("username", user.getName());
            model.addAttribute("user", user);
        }
    }

}
