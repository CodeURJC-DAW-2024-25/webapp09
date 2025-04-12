package es.grupo9.practica1.controller;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.grupo9.practica1.entities.Housing;
import es.grupo9.practica1.service.HousingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.IOException;
import java.sql.Blob;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/v1/api/houses/")
@Tag(name = "Housing Images Managing", description = "Endpoints for managing housing images")
public class HousingImageRestController {


    @Autowired
    private HousingService housingService; 



    
        @Operation(
        summary = "Get housing image",
        description = "Retrieves the image associated with a housing listing"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Image retrieved successfully",
            content = @Content(mediaType = "image/jpeg")
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Housing not found or has no image"
        )
    })
    @SecurityRequirement(name = "JWT")
    @GetMapping("/{id}/image")
    public ResponseEntity<ByteArrayResource> getImage(
        @Parameter(description = "ID(code) of the house which you desire to edit", example = "1", required = true)
        @PathVariable int id) throws SQLException {
        Housing housing = housingService.findByCode(id);
        if (housing == null || housing.getImage() == null) {
            return ResponseEntity.notFound().build();
        }

        Blob imageBlob = housing.getImage();
        byte[] bytes = imageBlob.getBytes(1, (int) imageBlob.length());
        ByteArrayResource resource = new ByteArrayResource(bytes);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // or MediaType.IMAGE_PNG
                .body(resource);
    }



    @Operation(
        summary = "Upload housing image",
        description = "Uploads or updates the image for a housing listing"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Image uploaded successfully"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid file format or empty file"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Housing not found"
        )
    })
    @SecurityRequirement(name = "JWT")
    @PutMapping("/{id}/image")
    public ResponseEntity<Void> uploadImage(
            @Parameter(description = "ID(code) of the house which you desire to edit", example = "1", required = true)
            @PathVariable int id,
            @Parameter(
            description = "Image file to upload (JPEG, PNG)",
            required = true,
            content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
            )
            @RequestParam("file") MultipartFile file) throws IOException, SQLException {
        
        Housing housing = housingService.findByCode(id);
        if (housing == null) {
            return ResponseEntity.notFound().build();
        }

        // Convert MultipartFile to Blob
        Blob imageBlob = new SerialBlob(file.getBytes());
        housing.setImage(imageBlob);
        housingService.saveImage(housing);

        return ResponseEntity.ok().build();
    }


        @Operation(
        summary = "Delete housing image",
        description = "Removes the image associated with a housing listing"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "204",
            description = "Image deleted successfully"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Housing not found"
        )
    })
    @SecurityRequirement(name = "JWT")
    @DeleteMapping("/{id}/image")
    public ResponseEntity<Void> deleteImage(@PathVariable int id){
        housingService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }


    
}
