package es.grupo9.practica1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public Usuario addUser(int dni, String nombre, int numero, String contrasenia, String correo){
    Usuario nuevoUsuario = new Usuario(dni, nombre, numero, contrasenia, correo);

    return userRepository.save(nuevoUsuario);


    }


    
}
