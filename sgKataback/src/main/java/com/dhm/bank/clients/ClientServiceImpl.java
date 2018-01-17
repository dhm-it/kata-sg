package com.dhm.bank.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements  ClientService {
  private final ClientRepository clientRepository ;
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client getClientById(String id) {
        if (!isClientExist(id)){
            throw  new UsernameNotFoundException("client not found") ;
        }
        return clientRepository.findOne(id);
    }

    @Override
    public Client getClientByUsername(String username) {
        if (!isUsernameExist(username))throw  new UsernameNotFoundException("client not found") ;
        return clientRepository.findByClientUsername(username);
    }

    @Override
    public void saveClient(Client client) {

        clientRepository.save(client);
    }

    @Override
    public void updateClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Boolean isClientExist(String id) {
        return clientRepository.findOne(id)!=null;
    }

    @Override
    public Boolean isUsernameExist(String username) {
        return clientRepository.findByClientUsername(username)!=null;
    }

    @Override
    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }
}
