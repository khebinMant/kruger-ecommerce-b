package krugers.microservicio.auth.authmicroservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties
public class ChangeCredentialsRequest {

	private String email;
	private String oldPassword;
	private String newPassword;
	private String code;
}
