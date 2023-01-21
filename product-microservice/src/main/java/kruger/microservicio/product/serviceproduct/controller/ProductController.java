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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import jakarta.validation.Valid;
import kruger.microservicio.product.serviceproduct.entity.Category;
import kruger.microservicio.product.serviceproduct.entity.Product;
import kruger.microservicio.product.serviceproduct.entity.Review;
import kruger.microservicio.product.serviceproduct.service.product.IProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * This microservice was created by Kevin Mantilla
 */
// @CrossOrigin(origins = "*")
@RestController
@RequestMapping (value = "api/products")
public class ProductController {

    @Autowired
    private IProductService iproductService;

    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "No content")
	})
    @Operation(summary = "Return a list with all products", description = "Returns a JSON response with the products information")
    @Tag(name = "GET all products ", description = "Retrieve information of all products")
    @GetMapping(value = "/all")
    public ResponseEntity<List<Product>> listProduct(@RequestParam(name="categoryId",required = false) Long categoryId ){
        List<Product> products = new ArrayList<>();
        if(categoryId == null){
            products = iproductService.listAllProducts();
            if(products.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }
        else{
            products = iproductService.findByCategory(Category.builder().id(categoryId).build());

            if(products.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.ok(products);
    }

    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "No content")
	})
    @Operation(summary = "Return a list with all products by name ", description = "Returns a JSON response with the products information")
    @Tag(name = "GET all products by name ", description = "Retrieve information of all products by name")
    @GetMapping(value = "/name")
    public ResponseEntity<List<Product>> listProductByName(@RequestParam(name="name",required = false) String name ){
        List<Product> products = new ArrayList<>();

        if(name == null){
            products = iproductService.listAllProducts();
            if(products.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }
        else{
            products = iproductService.findByName(name);

            if(products.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.ok(products);
    }

    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "No content")
	})
    @Operation(summary = "Return a product by Id", description = "Returns a JSON response with the product information")
    @Tag(name = "GET product by Id ", description = "Retrieve information of product by Id")
    @GetMapping(value="/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(name="id") Long id){
        Product product = iproductService.getProduct(id);

        if(product==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(product);
    }

    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "No content")
	})
    @Operation(summary = "Create a new category", description = "Returns a JSON response with the product information")
    @Tag(name = "POST product", description = "Retrieve information of a created product")
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result){
        
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,  this.formatMessage(result));
        }
        
        Product createdProduct = iproductService.createProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "We cant find a product with that id not found.")
	})
    @Operation(summary = "Update product", description = "Returns a JSON response with the product information")
    @Tag(name = "PUT update product information", description = "Retrieve information of a created product")
    @PutMapping(value="/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(name="id") Long id, @RequestBody Product product){
        product.setId(id);
        Product productDB = iproductService.updateProduct(product);
        if(productDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDB);
    }

    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "We cant find a product with that id not found.")
	})
    @Operation(summary = "Delete product", description = "Return OK status")
    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name="id") Long id){
        iproductService.deleteProduct(id);
        return  ResponseEntity.status(HttpStatus.OK).body("Successfully operation. ");
    }

    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "We cant find a product with that id not found.")
	})
    @Operation(summary = "Update product", description = "Returns a JSON response with the product information")
    @Tag(name = "PUT update product information", description = "Retrieve information of a created product")
    @GetMapping(value = "{id}/stock")
    public ResponseEntity<Product> updateStockProduct (@PathVariable(name="id")Long id, @RequestParam(name="quantity", required = true) Double quantity){
        Product product = iproductService.updateStock(id, quantity);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Successfully operation"),
		@ApiResponse(responseCode = "404", description = "We cant find a category with that id not found.")
	})
    @Operation(summary = "Update product", description = "Returns a JSON response with the product information")
    @Tag(name = "PUT update product information", description = "Retrieve information of a created product")
    @GetMapping(value = "{id}/counter")
    public ResponseEntity<Product> updateSaleCounter(@PathVariable(name="id")Long id, @RequestParam(name="quantity", required = true) Double quantity){
        Product product = iproductService.updateSaleCounter(id, quantity);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    private String formatMessage(BindingResult bindingResult){
        List<Map<String,String>> errors =  bindingResult.getFieldErrors().stream()
            .map(err -> {
                Map<String,String> error = new HashMap<>();
                error.put(err.getField(), err.getDefaultMessage());
                return error;
            }).collect(Collectors.toList());
        
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
