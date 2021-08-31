package com.everis.clientservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document ( collection = "customers" )
public class Customer {
		@Id
		private String idclient;
		private String dni;
		private String firstname;
		private String lastname;
		private String type;
}
