package com.sales.crm.controller;

import com.sales.crm.dtos.ClientRequestDTO;
import com.sales.crm.model.Client;
import com.sales.crm.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    private ClientService clientService;
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/client")
    private ResponseEntity<Client> addClientToUser(@RequestBody ClientRequestDTO clientRequestDTO){
        return ResponseEntity.ok(clientService.addClientToUser(clientRequestDTO));
    }
    @PostMapping("/client/{clientId}")
    private ResponseEntity<Client>updateClient(@PathVariable Long clientId, @RequestBody ClientRequestDTO clientRequestDTO){
        return ResponseEntity.ok(clientService.updateClient(clientId, clientRequestDTO));
    }

}
