package com.dhm.bank.clients;

import com.dhm.bank.ClientSecrets.ClientSecrets;
import com.dhm.bank.clientAccounts.ClientAccount;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

@Document
public class Client implements Serializable {

    @Id
    private String clientId ;
    private String clientUsername  ;
    @DBRef
    private ClientSecrets clientSecrets ;
    @DBRef
    private ClientAccount clientAccount ;
    public Client() {
    }

    public Client(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public ClientSecrets getClientSecrets() {
        return clientSecrets;
    }

    public void setClientSecrets(ClientSecrets clientSecrets) {
        this.clientSecrets = clientSecrets;
    }

    public ClientAccount getClientAccount() {
        return clientAccount;
    }

    public void setClientAccount(ClientAccount clientAccount) {
        this.clientAccount = clientAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(clientId, client.clientId) &&
                Objects.equals(clientUsername, client.clientUsername);
    }

    @Override
    public int hashCode() {

        return Objects.hash(clientId, clientUsername);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId='" + clientId + '\'' +
                ", clientUsername='" + clientUsername + '\'' +
                '}';
    }
}
