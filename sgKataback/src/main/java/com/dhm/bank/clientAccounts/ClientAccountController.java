package com.dhm.bank.clientAccounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@PreAuthorize("hasAuthority('ROLE_USER')")
public class ClientAccountController {
    private final ClientAccountService clientAccountService ;
    @Autowired
    public ClientAccountController(ClientAccountService clientAccountService) {
        this.clientAccountService = clientAccountService;
    }

    @PostMapping("/addAccount")
    public ResponseEntity<String> saveAcount(@RequestBody ClientAccount clientAccount){
        clientAccountService.addAccount(clientAccount);
        return new ResponseEntity<>("account saved with success", HttpStatus.OK);
    }
}
