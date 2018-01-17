package com.dhm.bank.ClientSecrets;

public interface ClientSecretService {
    String generateSecret() ;
    ClientSecrets getSecretById(String id) ;
    ClientSecrets getSecretBySecret(String secret) ;
    void saveSecret(ClientSecrets clientSecrets);
}
