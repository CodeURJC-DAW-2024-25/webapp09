package es.grupo9.practica1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.grupo9.practica1.entities.Housing;
import es.grupo9.practica1.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {


    Page<Review> findByHotel(Housing hotel, Pageable pageable); // el error esta aqui
    
} 
