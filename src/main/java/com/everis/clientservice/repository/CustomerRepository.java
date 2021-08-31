package com.everis.clientservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.everis.clientservice.model.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
		@Override
		boolean existsById(String id);

		boolean existsByFirstname(String firstname);

		boolean existsByLastname(String lastname);

		boolean existsByDni(String dni);

		Customer findByDni(String dni);
}
