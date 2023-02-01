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

import jakarta.validation.Valid;
import kruger.microservicio.product.serviceproduct.entity.Category;
import kruger.microservicio.product.serviceproduct.service.category.ICategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping (value = "api/categories")
public class CategoryController {

    @Autowired
    ICategoryService categoryService;

    //Obtener todas las categorias
    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "No content")
	})
    @Operation(summary = "Return a list with all categories", description = "Returns a JSON response with the categories information")
    @Tag(name = "GET all categories ", description = "Retrieve information of all categories")
    @GetMapping
    public ResponseEntity<List<Category>> listCategories(){
        List<Category> categories = new ArrayList<>();
        categories = categoryService.listAllCategories();
        if(categories.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(categories);
    }

    //Obtener categoria dado su id
    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "No content")
	})
    @Operation(summary = "Return a category by Id", description = "Returns a JSON response with the category information")
    @Tag(name = "GET category by Id ", description = "Retrieve information of category by Id")
    @GetMapping(value="/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable(name="id") Long id){
        Category category = categoryService.getCategory(id);

        if(category==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(category);
    }
    
    //Crear categoria
    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "No content")
	})
    @Operation(summary = "Create a new category", description = "Returns a JSON response with the category information")
    @Tag(name = "POST category", description = "Retrieve information of a created category")
    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category, BindingResult result){
        
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,  this.formatMessage(result));
        }
        
        Category createdCategory = categoryService.createCategory(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    //Actualizar la información de la categoria
    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "We cant find a category with that id not found.")
	})
    @Operation(summary = "Update category", description = "Returns a JSON response with the category information")
    @Tag(name = "PUT update category information", description = "Retrieve information of a created category")
    @PutMapping(value="/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable(name="id") Long id, @RequestBody Category category){
        category.setId(id);
        Category categoryDB = categoryService.updateCategory(category);
        if(categoryDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryDB);
    }

    //Eliminar una categoria
    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "We cant find a category with that id not found.")
	})
    @Operation(summary = "Delete category", description = "Return OK status")
    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(name="id") Long id){
      categoryService.deleteCategory(id);
      return  ResponseEntity.status(HttpStatus.OK).body("Successfully operation. ");
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
