package com.dhm.bank.clientAccounts;

public interface ClientAccountService {
    void addAccount(ClientAccount clientAccount);
    ClientAccount getClientAccountById(String id ) ;
    void updateClintAccount(ClientAccount clientAccount);
    Boolean withdraw(Float montant,ClientAccount clientAccount) ;

}
