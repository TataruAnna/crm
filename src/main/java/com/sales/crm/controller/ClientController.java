package com.sales.crm.controller;

import com.sales.crm.dtos.ClientRequestDTO;
import com.sales.crm.dtos.ClientResponseDTO;
import com.sales.crm.dtos.ClientSimpleResponseDTO;
import com.sales.crm.model.Category;
import com.sales.crm.model.Client;
import com.sales.crm.service.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@Tag(name = "Client", description = "Endpoints for clients ")
public class ClientController {
    private ClientService clientService;
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/add")
    public ResponseEntity<Client> addClientToUser(@RequestBody ClientRequestDTO clientRequestDTO){
        return ResponseEntity.ok(clientService.addClientToUser(clientRequestDTO));
    }
    @PostMapping("/update/{clientId}")
    public ResponseEntity<Client>updateClient(@PathVariable Long clientId, @RequestBody ClientRequestDTO clientRequestDTO){
        return ResponseEntity.ok(clientService.updateClient(clientId, clientRequestDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientSimpleResponseDTO>> findAll() {
        List<ClientSimpleResponseDTO> clients = clientService.findAll();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/view/{clientName}")
    public ResponseEntity<ClientResponseDTO> viewClientByName(@RequestParam String clientName){
        return  ResponseEntity.ok(clientService.viewClientDetailsByName(clientName));
    }

}
