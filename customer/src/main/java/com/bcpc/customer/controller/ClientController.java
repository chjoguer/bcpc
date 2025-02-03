package com.bcpc.customer.controller;

import com.bcpc.customer.controller.dto.ClientBankDTO;
import com.bcpc.customer.controller.dto.ClientDTO;
import com.bcpc.customer.service.implmentations.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("bcpc/api/clientes")
public class ClientController {
    @Autowired
    private ClientServiceImpl clientService;

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO client) {
        return ResponseEntity.ok(this.clientService.createClient(client));
    }
    @PostMapping("/create")
    public ResponseEntity<ClientBankDTO> createClientBank(@RequestBody ClientBankDTO clientDTO) {
        return ResponseEntity.ok(this.clientService.createClient2(clientDTO));
    }

    @PutMapping("/{identification}")
    public ResponseEntity<ClientBankDTO> updateClient(@RequestBody ClientBankDTO clientDTO,@PathVariable String identification) {
        return ResponseEntity.ok(this.clientService.updateClient(clientDTO,identification));
    }

    @GetMapping
    public ResponseEntity<List<ClientBankDTO>> fetchClients() {
        return ResponseEntity.ok(this.clientService.findAll());
    }

    @GetMapping("/{identification}")
    public ResponseEntity<ClientBankDTO> fetchClientByIdentification(@PathVariable("identification") String identification) {
        return  ResponseEntity.ok(this.clientService.findById(identification));
    }

    @DeleteMapping("{identification}")
    public ResponseEntity<ClientBankDTO> deleteClientByIdentification(@PathVariable("identification") String identification) {
        return  ResponseEntity.ok(this.clientService.deleteClient(identification));
    }


    // Add more endpoints for update, delete, find
}
