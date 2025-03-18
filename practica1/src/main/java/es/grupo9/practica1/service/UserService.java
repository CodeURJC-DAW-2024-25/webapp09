package es.grupo9.practica1.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import es.grupo9.practica1.controller.UserRestController;
import es.grupo9.practica1.entities.Admin;
import es.grupo9.practica1.entities.Client;
import es.grupo9.practica1.entities.User;
import es.grupo9.practica1.DTOs.*;
import es.grupo9.practica1.repository.UserRepository;
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


        String subject = "Registro exitoso en Trippins";
        String body = "Bienvenid@ a Trippins 🌎, " + name + " ✨\n\n" +
        "Tu aventura comienza aquí 🚀.\n\n" +
        "Tu cuenta ha sido creada con éxito ✅ y ahora tienes acceso a un mundo lleno de experiencias inolvidables 🌟.\n\n" +
        "Desde destinos paradisíacos 🏖️ hasta escapadas urbanas 🏙️, en Trippins hacemos que cada viaje sea único ✈️.\n\n" +
        "Prepárate para descubrir 🔍, explorar 🌍 y vivir aventuras increíbles 🎒🐵.\n\n" +
        "Si tienes alguna pregunta ❓, estamos aquí para ayudarte 🤗.\n\n" +
        "El equipo de desarrollo de Trippins 💻";
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

    public UserDTO getUserById(String dni){
        
        Optional<User> user = userRepository.findById(dni);
        return new UserDTO(user.get());

    }

    public UserDTO createUser(RegisteredUserDTO user){
        User newUser = new User(user.getDni(), user.getName(), user.getNumber(), user.getPassword(), user.getEmail());
        newUser.setEncodedPassword(passwordEncoder.encode(user.getPassword()));
        List<String> newRoles = user.getRoles();

        newUser.setRoles(newRoles);

        userRepository.save(newUser);
        return new UserDTO(newUser);


    }

    public UserDTO updateUser(String dni, RegisteredUserDTO user){
        Optional<User> originalUser = userRepository.findById(dni);
        User finalOriginalUser = originalUser.get();

        finalOriginalUser.setDni(user.getDni());
        finalOriginalUser.setName(user.getName());
        finalOriginalUser.setEmail(user.getEmail());
        finalOriginalUser.setNumber(user.getNumber());
        finalOriginalUser.setPassword(user.getPassword()); 
        finalOriginalUser.setEncodedPassword(passwordEncoder.encode(user.getPassword()));
        finalOriginalUser.setAdmin(user.getAdmin());
        finalOriginalUser.setRoles(user.getRoles());

        userRepository.save(finalOriginalUser);

        return new UserDTO(finalOriginalUser);


    }

    public void deleteUser(String dni){ //needs verification
        userRepository.deleteById(dni);

    }

    public List<UserDTO> getAllUsers(){
        List<User> userList= userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : userList) {
            UserDTO nuevoUser =new UserDTO(user);
            userDTOs.add(nuevoUser);
        }
        return userDTOs;
    }
}
