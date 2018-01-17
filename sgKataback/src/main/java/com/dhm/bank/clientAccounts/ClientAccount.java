package com.dhm.bank.clientAccounts;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Document
public class ClientAccount implements Serializable {
    @Id
    private String id ;
    private Boolean active ;
    private Float solde ;
    private Date lastAccess ;
    private Map<String ,String > secretQuestions ;

    public ClientAccount() {
    }

    public ClientAccount(Boolean active, Float solde, Date lastAccess, Map<String, String> secretQuestions) {
        this.active = active;
        this.solde = solde;
        this.lastAccess = lastAccess;
        this.secretQuestions = secretQuestions;
    }

    public String getId() {
        return id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Float getSolde() {
        return solde;
    }

    public void setSolde(Float solde) {
        this.solde = solde;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public Map<String, String> getSecretQuestions() {
        return secretQuestions;
    }

    public void setSecretQuestions(Map<String, String> secretQuestions) {
        this.secretQuestions = secretQuestions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientAccount that = (ClientAccount) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(active, that.active) &&
                Objects.equals(solde, that.solde) &&
                Objects.equals(lastAccess, that.lastAccess) &&
                Objects.equals(secretQuestions, that.secretQuestions);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, active, solde, lastAccess, secretQuestions);
    }

    @Override
    public String toString() {
        return "ClientAccount{" +
                "id='" + id + '\'' +
                ", active=" + active +
                ", solde=" + solde +
                ", lastAccess=" + lastAccess +
                ", secretQuestions=" + secretQuestions +
                '}';
    }
}
