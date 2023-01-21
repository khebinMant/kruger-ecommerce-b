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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kruger.microservicio.product.serviceproduct.entity.Review;
import kruger.microservicio.product.serviceproduct.service.review.IReviewService;

/**
 * This microservice was created by Kevin Mantilla
 */
@RestController
@RequestMapping (value = "api/reviews")
public class ReviewController {

    @Autowired
    IReviewService reviewService;


    @Tag(name = "Get all Reviews information")
    @GetMapping
    public ResponseEntity<List<Review>> listReviews(){
        List<Review> reviews = new ArrayList<>();
        reviews = reviewService.listAllReviews();
        if(reviews.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(reviews);
    }

    @Tag(name = "Get Review by ID")
    @GetMapping(value="/{id}")
    public ResponseEntity<Review> getReview(@PathVariable(name="id") Long id){
        Review review = reviewService.getReview(id);

        if(review==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(review);
    }
    
    @Tag(name = "Create a new review")
    @PostMapping
    public ResponseEntity<Review> createReview(@Valid @RequestBody Review review, BindingResult result){
        
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,  this.formatMessage(result));
        }
        
        Review createdReview = reviewService.createReview(review);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }

    @Tag(name = "Edit a review by ID")
    @PutMapping(value="/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable(name="id") Long id, @RequestBody Review review){
        review.setId(id);
        Review reviewDB = reviewService.updateReview(review);
        if(reviewDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reviewDB);
    }

    @Tag(name = "Delete specific review info")
    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable(name="id") Long id){
        reviewService.deleteReview(id);

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
