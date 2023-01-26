package kruger.microservicio.product.serviceproduct.entity;


import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data 
@AllArgsConstructor @NoArgsConstructor
@Builder

/**
 * This microservice was created by Kevin Mantilla
 */
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message="El nombre del campo no debe de ser vacio")
    private String name;

    @Column(columnDefinition="TEXT")
    private String description;

    @Column(columnDefinition="TEXT",name = "youtube_link")
    private String youtubeLink;
    
    private Double price;

    //Es la cantidad
    @Positive(message = "El stock debe ser mayor que cero")
    private Double stock;

    private Status status;

    private Type type;

    private String brand;

    private Double weight;

    private String processor;

    @Column(name = "sales_counter")
    private Double salesCounter;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @NotNull(message = "La categoria no puede ser vacia")    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Category category;
    
    @Valid
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<Review> reviews;

    @Valid
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<Image> images;
}
