package com.dhm.bank.clients;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client ,String> {

    Client findByClientUsername(String username);
}
