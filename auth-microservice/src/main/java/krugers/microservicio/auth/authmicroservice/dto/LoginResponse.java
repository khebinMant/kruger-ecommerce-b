package krugers.microservicio.auth.authmicroservice.dto;

import java.util.Date;
import java.util.List;

import krugers.microservicio.auth.authmicroservice.entity.Address;
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

    private String cellPhone;

    private Date birthDate;

    private Date signDate;

    private String imageUrl;
    
    private Long companyId;

    private Boolean verified;
    
    private Role role;

    private List<Address> addresses;

    private String token;
    
    

}
