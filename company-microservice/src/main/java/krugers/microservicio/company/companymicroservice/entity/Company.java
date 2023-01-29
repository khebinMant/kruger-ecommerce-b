package krugers.microservicio.company.companymicroservice.entity;


import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="companies")
public class Company implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no puede ser vacío")
    @Column(name="company_name", nullable=false)
    private String companyName;

    private String description;

    private String objective;

    private String history;
    
    private String contact;
    
    @Column(name="image_url")
    private String imageUrl;

    private String status;

    @Temporal(TemporalType.DATE)
    @Column(name="create_at")
    private Date createAt;

    @Temporal(TemporalType.DATE)
    @Column(name="update_at")
    private Date updateAt;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

}
