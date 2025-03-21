package es.grupo9.practica1.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.grupo9.practica1.DTOs.HousingDTO;
import es.grupo9.practica1.DTOs.TagDTO;
import es.grupo9.practica1.service.HousingService;

@RestController
@RequestMapping("/api/houses")
public class HousingRestController {



    @Autowired
    HousingService housingService;





    @GetMapping
    public ResponseEntity<List<HousingDTO>> getAllhouses() {
        List<HousingDTO> houses = housingService.getAllHouses();
        return ResponseEntity.ok(houses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HousingDTO> getHouseById(@PathVariable Integer id) {
        HousingDTO house = housingService.getHouseById(id);
        return ResponseEntity.ok(house);
    }

    @PostMapping
    public ResponseEntity<HousingDTO> createHouse(@RequestBody HousingDTO house) {
        HousingDTO createdHouse = housingService.createHouse(house);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHouse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HousingDTO> updateHouse(@PathVariable int id, @RequestBody HousingDTO house) {
        HousingDTO updatedHouse = housingService.updateHouse(id, house);
        return ResponseEntity.ok(updatedHouse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHouse(@PathVariable int id) {
        housingService.deleteHouse(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}/tags")
    public ResponseEntity<Set<TagDTO>> getTagsById(@PathVariable int id) {
        Set<TagDTO> tags = housingService.getTagsById(id);
        return ResponseEntity.ok(tags);
    }

    
}
