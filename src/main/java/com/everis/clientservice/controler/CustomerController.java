package com.everis.clientservice.controler;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.everis.clientservice.dto.CustomerFrom;
import com.everis.clientservice.dto.Message;
import com.everis.clientservice.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin ( origins = "*", methods = {RequestMethod.GET , RequestMethod.POST , RequestMethod.PUT , RequestMethod.DELETE} )
@RequestMapping
public class CustomerController {

		@Autowired
		private CustomerService serviceCustomer;

		@GetMapping ( "/" )
		public Flux<Object> list() {
				return serviceCustomer.listAll();
		}

		@GetMapping ( "/dni/{dni}" )
		public Mono<Object> findByDni(@PathVariable ( "dni" ) String dni) {
				return serviceCustomer.searchByDni(dni);
		}

		@GetMapping ( "/{id}" )
		public Mono<Object> details(@PathVariable ( "id" ) String id) {
				return serviceCustomer.detailsClient(id);
		}

		@GetMapping ( "/verifyId/{id}" )
		public Mono<Boolean> verify(@PathVariable ( "id" ) String id) {
				return serviceCustomer.verifyId(id);
		}

		@PutMapping ( "/update/{id}" )
		public Mono<Object> update(@PathVariable ( "id" ) String id , @RequestBody @Valid CustomerFrom from , BindingResult bindinResult) {
				String msg = "";

				if ( bindinResult.hasErrors() ) {
						for ( int i = 0 ; i < bindinResult.getAllErrors().size() ; i++ ) {
								msg = bindinResult.getAllErrors().get(0).getDefaultMessage();
						}
						return Mono.just(new Message(msg));
				}

				return serviceCustomer.update(id , from.getDni() , from.getFirstname() , from.getLastname() , from.getTypecustomer());
		}

		@PostMapping ( "/save" )
		public Mono<Object> create(@RequestBody @Valid CustomerFrom from , BindingResult bindinResult) {
				String msg = "";

				if ( bindinResult.hasErrors() ) {
						for ( int i = 0 ; i < bindinResult.getAllErrors().size() ; i++ ) {
								msg = bindinResult.getAllErrors().get(0).getDefaultMessage();
						}
						return Mono.just(new Message(msg));
				}

				return serviceCustomer.register(from.getDni() , from.getFirstname() , from.getLastname() , from.getTypecustomer());
		}

		@DeleteMapping ( "/delete/{id}" )
		public Mono<Object> update(@PathVariable ( "id" ) String id) {
				return serviceCustomer.delete(id);
		}
}
