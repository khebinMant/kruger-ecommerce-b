package krugers.microservicio.auth.authmicroservice.dto;

import java.util.Date;
import java.util.List;

import krugers.microservicio.auth.authmicroservice.entity.Cart;
import krugers.microservicio.auth.authmicroservice.entity.Role;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LoginResponse {
    
    private Long id;

    private String email;

    private String password;

    private String userName;

    private String firstName;

    private String lastName;

    private Date signDate;

    private Boolean verified;
    
    private Role role;

    private List<Cart> carts;

    private String token;

}
