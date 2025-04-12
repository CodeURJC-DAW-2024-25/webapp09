package es.grupo9.practica1.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.grupo9.practica1.entities.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
    @Transactional
    default Tag findOrCreate(String tagName) {
        return findById(tagName)
                .orElseGet(() -> {
                    Tag newTag = new Tag(tagName);
                    return save(newTag); // Save the new tag to the database
                });
    }
}
