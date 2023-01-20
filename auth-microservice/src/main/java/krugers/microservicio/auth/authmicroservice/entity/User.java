package krugers.microservicio.auth.authmicroservice.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "sign_date")
    private Date signDate;

    private Boolean verified; //TRUE OR FALSE EMAIL VERIFICATION
    
    @Column(name = "role", nullable = false)
    private Role role;

//     @OneToMany(mappedBy = "userId",cascade = CascadeType.ALL)
//    private List<Cart> carts;

//    public void addCard(Cart cart){
//         if(carts==null){
//             carts=new ArrayList(){};
//         }
//         if(!carts.contains(cart)){
//             carts.add(cart);
//             cart.setUserId(this.getId());
//         }
//     }


}
