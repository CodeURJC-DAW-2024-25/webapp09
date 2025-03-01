package es.grupo9.practica1;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface HousingRepository extends JpaRepository<Housing, Integer> {
    @Query("SELECT MAX(code) FROM Housing")
    Integer maxhotelCode();


    Page<Housing> findByAceptedFalse(Pageable pageable);
    Page<Housing> findByAceptedTrue(Pageable pageable);
}