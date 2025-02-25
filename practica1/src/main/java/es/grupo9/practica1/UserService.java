package es.grupo9.practica1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
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
        
        return userRepository.save(newUser);

    }



    
}
