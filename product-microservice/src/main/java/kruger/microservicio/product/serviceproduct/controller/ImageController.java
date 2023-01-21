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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kruger.microservicio.product.serviceproduct.entity.Image;
import kruger.microservicio.product.serviceproduct.service.image.IImageService;

@RestController
@RequestMapping (value = "api/images")
public class ImageController {
    
    @Autowired
    IImageService imageService;

    @Tag(name = "Get all images information")
    @GetMapping
    public ResponseEntity<List<Image>> listImages(){
        List<Image> images = new ArrayList<>();
        images = imageService.listAllImages();
        if(images.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(images);
    }

    @Tag(name = "Get image by ID")
    @GetMapping(value="/{id}")
    public ResponseEntity<Image> getImage(@PathVariable(name="id") Long id){
        Image image = imageService.getImage(id);

        if(image==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(image);
    }
    
    @Tag(name = "Create a new image")
    @PostMapping
    public ResponseEntity<Image> createImage(@Valid @RequestBody Image image, BindingResult result){
        
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,  this.formatMessage(result));
        }
        
        Image createdImage = imageService.createImage(image);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdImage);
    }

    @Tag(name = "Edit a review by ID")
    @PutMapping(value="/{id}")
    public ResponseEntity<Image> updateImage(@PathVariable(name="id") Long id, @RequestBody Image image){
        image.setId(id);
        Image imageDB = imageService.updateImage(image);
        if(imageDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(imageDB);
    }

    @Tag(name = "Delete specific review info")
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
