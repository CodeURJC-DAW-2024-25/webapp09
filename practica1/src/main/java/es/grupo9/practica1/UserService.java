package es.grupo9.practica1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public void userlogin(String email, String password) {
        // Obtener la contraseña desde el repositorio por el email
        String pass = userRepository.getPasswordByEmail(email);
        
        // Comparar la contraseña obtenida con la proporcionada
        if (pass.equals(password)) {
            System.out.println("Login correcto");
        } else {
            System.out.println("Email o contraseña incorrectos");
        }
    }





    public User addUser(String dni, String name, Integer number, String password, String email){
        User newUser = new Client(dni, name, number, password, email);
        newUser.setEncodedPassword(passwordEncoder.encode(password));
        List<String> newRoles = new ArrayList<String>();
        newRoles.add("USER");

        newUser.setRoles(newRoles);
        return userRepository.save(newUser);

    }
    @PostConstruct
    public void initializeUsers() {
        // Inicialización de los usuarios en la base de datos si no existen
        User adminUser = new Admin("12345678A", "Admin1", 123456789, "admin", "trippins.urjc@gmail.com");
        List<String> newRoles = new ArrayList<String>();
        newRoles.add("ADMIN");

        adminUser.setRoles(newRoles);
        userRepository.save(adminUser);
    }
}
