package es.grupo9.practica1;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
    @Query("SELECT u.password FROM User u WHERE u.email = :user")
    public String getPasswordByEmail(@Param("user") String email);
}
