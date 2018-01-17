package com.dhm.bank.clientAccounts;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientAccountRepository extends MongoRepository<ClientAccount ,String> {

}
