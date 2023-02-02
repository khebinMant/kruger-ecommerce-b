package krugers.microservicio.auth.authmicroservice.utils;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

//singletone class
public final  class RecoveryCodesStore {
	
	private static RecoveryCodesStore INSTANCE;
	private Map<String, String> codes;

	private RecoveryCodesStore() {
		codes = new HashMap<>();
	}

	public static RecoveryCodesStore getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new RecoveryCodesStore();
		}

		return INSTANCE;
	}

	public void addCode(String email, String code) {
		if (!this.codes.containsKey(email)) {
			this.codes.put(email, code);
		} else {
			this.codes.replace(email, code);
		}
	}

	public boolean validateCode(String email, String code) {
		if(codes.containsKey(email)) {
		if (codes.get(email).equals(code)) {
			return true;
		}
		}
		return false;

	}
	
	public boolean removeCode (String email, String code) {
		if(codes.containsKey(email)) {
			if (codes.get(email).equals(code)) {
				return true;
			}
	}
		return false;
		}
}
