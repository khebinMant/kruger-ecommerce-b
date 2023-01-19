package krugers.microservicio.auth.authmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * This microservice was created by Kenan Aljaber
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    
    private Long userId;
    private Long orderId;
}
