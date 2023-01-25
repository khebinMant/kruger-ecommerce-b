package krugers.microservicio.auth.authmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ChangeCredentialsRequest {

	private String email;
	private String oldPassword;
	private String newPassword;
}
