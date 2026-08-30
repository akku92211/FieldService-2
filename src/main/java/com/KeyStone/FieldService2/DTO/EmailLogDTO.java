package com.KeyStone.FieldService2.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailLogDTO {

	public String receptientEmail;
	public String subject;
	public String body;
}
