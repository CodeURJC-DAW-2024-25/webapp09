package es.grupo9.practica1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public Usuario addUser(Integer dni, String nombre, Integer numero, String contrasenia, String correo){
        Usuario nuevoUsuario = new Usuario(dni, nombre, numero, contrasenia, correo);
        nuevoUsuario.setAdmin(false);
        System.out.println(nuevoUsuario.toString());
        return userRepository.save(nuevoUsuario);


    }


    
}
