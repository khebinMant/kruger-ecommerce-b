package kruger.microservicio.product.serviceproduct.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import kruger.microservicio.product.serviceproduct.entity.Image;
import kruger.microservicio.product.serviceproduct.service.image.IImageService;

@RestController
@RequestMapping (value = "api/images")
public class ImageController {
    
    @Autowired
    IImageService imageService;

    //Obtener todas las imágenes
    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "No content")
	})
    @Operation(summary = "Return a list with all images", description = "Returns a JSON response with the images information")
    @Tag(name = "GET all images ", description = "Retrieve all images")
    @GetMapping
    public ResponseEntity<List<Image>> listImages(){
        List<Image> images = new ArrayList<>();
        images = imageService.listAllImages();
        if(images.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(images);
    }


    //Obtener imagen dado su id
    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "No content")
	})
    @Operation(summary = "Return a image by Id", description = "Returns a JSON response with the image information")
    @Tag(name = "GET image by Id ", description = "Retrieve information of image by Id")
    @GetMapping(value="/{id}")
    public ResponseEntity<Image> getImage(@PathVariable(name="id") Long id){
        Image image = imageService.getImage(id);

        if(image==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(image);
    }
    
    //Crear imagen
    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "No content")
	})
    @Operation(summary = "Create a new image", description = "Returns a JSON response with the image information")
    @Tag(name = "POST image", description = "Retrieve information of a created image")
    @PostMapping
    public ResponseEntity<Image> createImage(@Valid @RequestBody Image image, BindingResult result){
        
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,  this.formatMessage(result));
        }
        
        Image createdImage = imageService.createImage(image);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdImage);
    }

    //Actualizar la información de la imagen
    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "We cant find a image with that id not found.")
	})
    @Operation(summary = "Update image", description = "Returns a JSON response with the image information")
    @Tag(name = "PUT update image information", description = "Retrieve information of a created image")
    @PutMapping(value="/{id}")
    public ResponseEntity<Image> updateImage(@PathVariable(name="id") Long id, @RequestBody Image image){
        image.setId(id);
        Image imageDB = imageService.updateImage(image);
        if(imageDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(imageDB);
    }

    //Eliminar una imagen
    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "We cant find a image with that id not found.")
	})
    @Operation(summary = "Delete image", description = "Return OK status")
    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable(name="id") Long id){
        imageService.deleteImage(id);

      return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Successfully operation. ");
    }


    private String formatMessage(BindingResult bindingResult){
        List<Map<String,String>> errors =  bindingResult.getFieldErrors().stream()
            .map(err -> {
                Map<String,String> error = new HashMap<>();
                error.put(err.getField(), err.getDefaultMessage());
                return error;
            }).collect(Collectors.toList());
        
        //Aqui guardo utilizando la clase que creamos para gestionar el error
        ErrorMessage errorMessage = ErrorMessage.builder()
            .code("01")
            .messages(errors).build();

        ObjectMapper mapper = new ObjectMapper();

        String jsoString="";
        try{
            jsoString = mapper.writeValueAsString(errorMessage);
        }
        catch(JsonProcessingException e){
            e.printStackTrace();
        }
        return jsoString;
    } 
}
