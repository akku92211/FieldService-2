package com.KeyStone.FieldService2.DTO;

public class AuthResponseDTO {

	public String token;
	public String message;

	public AuthResponseDTO(String token,String message) {
		this.token=token;
		this.message=message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

}
