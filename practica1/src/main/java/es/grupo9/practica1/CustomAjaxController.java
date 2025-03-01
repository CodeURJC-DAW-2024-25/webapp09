package es.grupo9.practica1;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;

@RestController
public class CustomAjaxController {

    @Autowired
    private HousingRepository housingRepository;

    @Autowired
    private PagedResourcesAssembler<Housing> pagedResourcesAssembler;

    @PostMapping("/roomHouses")
    public Page<Housing> getHouses(@RequestBody Map<String, Integer> request) {
        try {
            // Extract page and size from the request body
            int page = request.getOrDefault("page", 0); // Default to page 0 if not provided
            int size = request.getOrDefault("size", 6); // Default to size 6 if not provided

            // Log the received parameters
            System.out.println("Received request - page: " + page + ", size: " + size);

            // Fetch the houses using pagination
            Pageable pageable = PageRequest.of(page, size);
            Page<Housing> houses = housingRepository.findAll(pageable);

            // Log the number of houses fetched
            System.out.println("Fetched " + houses.getNumberOfElements() + " houses");

            return houses;
        } catch (Exception e) {
            // Log the error
            System.err.println("Error fetching houses: " + e.getMessage());
            throw e; // Re-throw the exception to return a 500 error
        }
    }

    @PostMapping("/adminHouses")
    public Page<Housing> getAdminHouses(@RequestBody Map<String, Integer> request) {
        try {
            // Extract page and size from the request body
            int page = request.getOrDefault("page", 0); // Default to page 0 if not provided
            int size = request.getOrDefault("size", 6); // Default to size 6 if not provided

            // Log the received parameters
            System.out.println("Received request - page: " + page + ", size: " + size);

            // Fetch the houses using pagination
            Pageable pageable = PageRequest.of(page, size);
            
            Page<Housing> filteredPage = housingRepository.findByAceptedFalse(pageable);
            
            
            return filteredPage;
        } catch (Exception e) {
            // Log the error
            System.err.println("Error fetching houses: " + e.getMessage());
            throw e; // Re-throw the exception to return a 500 error
        }
    }

}
