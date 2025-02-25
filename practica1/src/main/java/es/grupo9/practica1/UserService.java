package es.grupo9.practica1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User addUser(String dni, String name, Integer number, String password, String email){
        User newUser = new Client(dni, name, number, password, email);
        
        return userRepository.save(newUser);

    }


    
}
