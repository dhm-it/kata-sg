package com.dhm.bank.ClientSecrets;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

@Document
public class ClientSecrets implements Serializable {

    @Id
    private String id ;
    private String secret ;

    public ClientSecrets() {
    }

    public ClientSecrets(String secret) {
        this.secret = secret;
    }

    public String getId() {
        return id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientSecrets that = (ClientSecrets) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(secret, that.secret);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, secret);
    }

    @Override
    public String toString() {
        return "ClientSecrets{" +
                "id='" + id + '\'' +
                ", secret='" + secret + '\'' +
                '}';
    }
}
