package kruger.microservicio.product.serviceproduct.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class User {

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

    private List<Address> addresses;

    private Boolean verified; //TRUE OR FALSE EMAIL VERIFICATION
    
    private Role role; 
}
