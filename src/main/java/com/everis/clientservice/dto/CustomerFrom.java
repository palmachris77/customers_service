package com.everis.clientservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFrom {

		@NotBlank ( message = "El campo dni no debe estar vacio." )
		@Size ( min = 8, message = "El campo dni de tener mas de 8 caracteres como maximo." )
		private String dni;

		@NotBlank ( message = "El campo nombre no debe estar vacio." )
		@Size ( min = 3, message = "El campo nombre de tener mas de 3 caracteres como maximo." )
		private String firstname;

		@NotBlank ( message = "El campo apellido no debe estar vacio." )
		@Size ( min = 4, message = "El campo apellido de tener mas de 4 caracteres como maximo." )
		private String lastname;

		@NotBlank ( message = "El campo tipo no debe estar vacio." )
		private String typecustomer;
}
