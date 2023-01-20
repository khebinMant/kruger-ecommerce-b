package krugers.microservicio.auth.authmicroservice.entity;

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
public class Address implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "La provincia no puede estar vacía no puede ser vacío")
    @Column(name="province", nullable=false)
    private String province;

    @NotEmpty(message = "La ciudad no puede estar vacía no puede ser vacío")
    @Column(name="city", nullable=false)
    private String city;

    @NotEmpty(message = "La dirección no puede estar vacía no puede ser vacío")
    @Column(name="address", nullable=false)
    private String address;

    @NotEmpty(message = "La calle no puede estar vacía no puede ser vacío")
    @Column(name="street", nullable=false)
    private String street;

    @Column(name="is_matriz")
    private Boolean isMatriz;

    @Column(name = "userId")
    private Long userId;

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
