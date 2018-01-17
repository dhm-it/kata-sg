package com.dhm.bank.ClientSecrets;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientSecretRepository extends MongoRepository<ClientSecrets,String> {
    ClientSecrets findBySecret(String secret) ;
}
