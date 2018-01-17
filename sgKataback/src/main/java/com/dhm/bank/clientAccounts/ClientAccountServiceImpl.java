package com.dhm.bank.clientAccounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.NotActiveException;

@Service
public class ClientAccountServiceImpl implements ClientAccountService {
   private final ClientAccountRepository clientAccountRepository ;
    @Autowired
    public ClientAccountServiceImpl(ClientAccountRepository clientAccountRepository) {
        this.clientAccountRepository = clientAccountRepository;
    }

    /**
     * Save a new account of a user
     * @param clientAccount
     */
    @Override
    public void addAccount(ClientAccount clientAccount) {
        clientAccountRepository.save(clientAccount);
    }

    /**
     * Get Client account
     * @param id
     * @return
     */
    @Override
    public ClientAccount getClientAccountById(String id) {
        return clientAccountRepository.findOne(id);
    }

    /**
     * Update client account
     * @param clientAccount
     */
    @Override
    public void updateClintAccount(ClientAccount clientAccount) {
        clientAccountRepository.save(clientAccount);
    }

    /**
     * Withdraw from account
     * @param montant
     * @return
     */
    @Override
    public Boolean withdraw(Float montant,ClientAccount clientAccount) {
        if (!clientAccount.getActive()|| clientAccount.getSolde()<montant) return false ;
        clientAccount.setSolde(clientAccount.getSolde()-montant);
        return true;
    }
}
