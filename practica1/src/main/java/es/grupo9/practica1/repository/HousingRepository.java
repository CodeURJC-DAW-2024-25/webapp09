package es.grupo9.practica1.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.grupo9.practica1.entities.Housing;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface HousingRepository extends JpaRepository<Housing, Integer> {
    @Query("SELECT MAX(code) FROM Housing")
    Integer maxhotelCode();


    Page<Housing> findByAceptedFalse(Pageable pageable);
    Page<Housing> findByAceptedTrue(Pageable pageable);
    Optional<Housing> findByCode(Integer code);



    List<Housing> findByStarsGreaterThanEqual(Integer stars);

    // Query to find houses with specific tags and at least the specified stars
    @Query("SELECT h FROM Housing h " +
           "JOIN h.tags t " +
           "WHERE t.id IN :tagNames AND h.stars >= :stars " +
           "GROUP BY h.code " +
           "HAVING COUNT(DISTINCT t.id) = :tagCount")
    List<Housing> findByTagsAndStars(@Param("tagNames") Set<String> tagNames,
                                    @Param("stars") Integer stars,
                                    @Param("tagCount") Long tagCount);
}