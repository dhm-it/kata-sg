package com.dhm.bank.clients;

import java.util.List;

public interface ClientService {
    Client getClientById(String id ) ;
    Client getClientByUsername(String username );
    void saveClient(Client client) ;
    void updateClient(Client client) ;
    Boolean isClientExist(String id) ;
    Boolean isUsernameExist(String username );
    List<Client> getAllClient();
}
