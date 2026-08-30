package com.KeyStone.FieldService2.DTO;

import com.KeyStone.FieldService2.Enum.Role;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDTO {

	public String userName;
	public String userEmail;
	public String password;
	public String phone;
	public Role role;
	

}
