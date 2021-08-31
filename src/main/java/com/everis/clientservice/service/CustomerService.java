package com.everis.clientservice.service;

import com.everis.clientservice.Constants.Constants;
import com.everis.clientservice.dto.Message;
import com.everis.clientservice.model.Customer;
import com.everis.clientservice.model.TypeCustomers;
import com.everis.clientservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CustomerService {

  @Autowired
  CustomerRepository repository;

  public Mono<Boolean> verifyId(String id) {
    return Mono.just(repository.existsById(id));
  }

  public Customer searchClient(String id) {
    return repository.findById(id).get();
  }

  public Mono<Object> save(Customer obj) {
    String m = Constants.Messages.CORRECT_DATA;

    if (
      TypeCustomers.empresial().equals(obj.getType()) ||
      TypeCustomers.personal().equals(obj.getType())
    ) {
      try {
        if (
          (
            repository.existsByFirstname(obj.getFirstname()) &&
            repository.existsByLastname(obj.getLastname())
          ) ||
          repository.existsByDni(obj.getDni())
        ) {
          m = Constants.Messages.REPET_DATA;
        } else {
          repository.save(obj);
        }
      } catch (Exception e) {
        m = Constants.Messages.INVALID_DATA;
      }
    } else {
      m = Constants.Messages.INVALID_DATA;
    }

    return Mono.just(new Message(m));
  }

  public Mono<Object> detailsClient(String id) {
    if (repository.existsById(id)) {
      return Mono.just(searchClient(id));
    }

    return Mono.just(new Message(Constants.Messages.CLIENT_NOT_FOUND));
  }

  public Mono<Object> update(
    String id,
    String dni,
    String firstname,
    String lastname,
    String typecustomer
  ) {
    if (repository.existsById(id)) {
      Customer obj = new Customer(id, dni, firstname, lastname, typecustomer);
      return save(obj);
    }
    return Mono.just(new Message(Constants.Messages.CLIENT_NOT_FOUND));
  }

  public Mono<Object> register(
    String dni,
    String firstname,
    String lastname,
    String typecustomer
  ) {
    Customer obj = new Customer(null, dni, firstname, lastname, typecustomer);
    return save(obj);
  }

  public Mono<Object> delete(String id) {
    String m = Constants.Messages.CLIENT_NOT_FOUND;

    if (repository.existsById(id)) {
      repository.deleteById(id);
      m = Constants.Messages.CLIENT_DELETED_SUCCESS;
    }

    return Mono.just(new Message(m));
  }

  public Mono<Object> searchByDni(String dni) {
    if (repository.existsByDni(dni)) {
      return Mono.just(repository.findByDni(dni));
    }
    return Mono.just(new Message(Constants.Messages.CLIENT_NOT_FOUND));
  }

  public Flux<Object> listAll() {
    return Flux.fromIterable(repository.findAll());
  }
}
