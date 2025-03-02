package es.grupo9.practica1;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HousingRepository extends JpaRepository<Housing, Integer> {
    @Query("SELECT COALESCE(MAX(h.id), 0) FROM Housing h")
    Integer maxhotelCode();

    Page<Housing> findByAceptedFalse(Pageable pageable);
    Page<Housing> findByAceptedTrue(Pageable pageable);

    @Query("SELECT h FROM Housing h WHERE h.stars >= :stars AND h.tags LIKE %:tags%")
    Page<Housing> findByStarsAndTags(int stars, String tags, Pageable pageable);
}