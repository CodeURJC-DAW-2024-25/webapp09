package es.grupo9.practica1;

import java.util.ArrayList;
import java.util.Arrays;
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
        
        //We need to make it so the compared and stored thing here is the encoded password

        // Comparar la contraseña obtenida con la proporcionada
        if (pass.equals(password)) {
            System.out.println("Login correcto");
        } else {
            System.out.println("Email o contraseña incorrectos");
        }
    }



    @Autowired
    private EmailService emailService;

    public User addUser(String dni, String name, Integer number, String password, String email){
        User newUser = new Client(dni, name, number, password, email);
        newUser.setEncodedPassword(passwordEncoder.encode(password));
        List<String> newRoles = new ArrayList<String>();
        newRoles.add("USER");

        newUser.setRoles(newRoles);


        String subject = "Bienvenido a TriPPins🐒";
        String body = "Bienvenid@ a TriPPins " + name + " 😊,\n\n" +
        "Tu aventura comienza aquí. 🌍✈️\n\n" +
        "Tu cuenta ha sido creada con éxito y ahora tienes acceso a un mundo lleno de experiencias inolvidables. 🎒📸\n\n" +
        "Desde destinos paradisíacos hasta escapadas urbanas, en Trippins hacemos que cada viaje sea único. 🏝️🏙️\n\n" +
        "Prepárate para descubrir, explorar y vivir aventuras increíbles. 🚀✨\n\n" +
        "Si tienes alguna pregunta, estamos aquí para ayudarte. 💬\n\n" +
        "El equipo de desarrollo de Trippins ❤️";
        emailService.sendEmail(email, subject, body);

        return userRepository.save(newUser);

    }
    @PostConstruct
    public void initializeUsers() {

    // Lista de usuarios administradores
    List<User> adminUsers = Arrays.asList(
        new Admin("12345678A", "Admin", 123456789, "admin", "trippins.urjc@gmail.com"),
        new Admin("11223344C", "Admin3", 112233445, "admin", "admin3@trippins.com")
        
    );
    //Un user cliente de prueba
    List<String> rolespepe = new ArrayList<>();
    rolespepe.add("USER");
    Client pepe = new Client("11223348C", "Pepe", 333333333, "pepe", "pepe@trippins.com");
    pepe.setRoles(rolespepe);
    pepe.setEncodedPassword(passwordEncoder.encode("pepe"));
    userRepository.save(pepe);

    // Asignar roles y guardar los usuarios
    for (User user : adminUsers) {
        List<String> roles = new ArrayList<>();
        roles.add("ADMIN"); // Asignar el rol de administrador
        user.setRoles(roles);
        user.setEncodedPassword(passwordEncoder.encode("admin"));
        userRepository.save(user);
    }
}
}
