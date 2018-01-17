package com.dhm.bank.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService  ;
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/getClient")
    public ResponseEntity<Client> getAClientByUsername(@RequestBody String username){
        return new ResponseEntity<>(clientService.getClientByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/saveClient")
    public ResponseEntity<String> saveClient(@RequestBody Client client){
        clientService.saveClient(client);
        return new ResponseEntity<>("client saved with success",HttpStatus.OK);
    }

    @GetMapping("/allClient")
    public ResponseEntity<List<Client>> getAllClient(){
        return new ResponseEntity<>(clientService.getAllClient(),HttpStatus.OK);
    }



}
