package com.tsk.ecommerce.dtos.requests;


import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class LoginRequestDto {

	@NotBlank(message = "Username is mandatory")
	private String username;

	@NotBlank(message = "Password is mandatory")
	private String password;

	public LoginRequestDto(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
