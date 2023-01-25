package krugers.microservicio.auth.authmicroservice.dto;

public enum ChangeCredentialsResponse {
	CHANGED,
	EMAIL_DOES_NOT_MATCH,	
	USER_ID_NOT_FOUND,
	WRONG_OLD_PASSWORD

}
