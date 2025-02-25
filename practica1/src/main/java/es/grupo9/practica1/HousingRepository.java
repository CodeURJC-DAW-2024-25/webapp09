package es.grupo9.practica1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HousingRepository extends JpaRepository<Housing, Integer> {
    @Query("SELECT MAX(code) FROM Housing")
    Integer maxhotelCode();
}