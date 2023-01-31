package krugers.microservicio.auth.authmicroservice.model;

import java.util.Date;

import lombok.Data;

@Data
public class Category {

    private Long id;

    private String name;    

    private String description;

    private Date created;
}
