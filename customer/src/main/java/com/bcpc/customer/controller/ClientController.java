package com.bcpc.customer.controller;

import com.bcpc.customer.controller.dto.ClientBankDTO;
import com.bcpc.customer.controller.dto.ClientDTO;
import com.bcpc.customer.service.implmentations.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("bcpc/api/clientes")
public class ClientController {
    @Autowired
    private ClientServiceImpl clientService;


    @PostMapping("/create")
    public ResponseEntity<ClientBankDTO> createClientBank(@RequestBody ClientBankDTO clientDTO) {
        return ResponseEntity.ok(this.clientService.createClient(clientDTO));
    }



    @GetMapping
    public ResponseEntity<List<ClientBankDTO>> fetchClients() {
        return ResponseEntity.ok(this.clientService.findAll());
    }

    @GetMapping("/{identification}")
    public ResponseEntity<ClientBankDTO> fetchClientByIdentification(@PathVariable("identification") String identification) {
        return  ResponseEntity.ok(this.clientService.findById(identification));
    }

    @PostMapping()
    public ResponseEntity<ClientBankDTO> createClient(@RequestBody ClientBankDTO client) {
        return ResponseEntity.ok(this.clientService.createClient(client));
    }

    @PutMapping("/{identification}")
    public ResponseEntity<ClientBankDTO> updateClient(@RequestBody ClientBankDTO clientDTO,@PathVariable String identification) {
        return ResponseEntity.ok(this.clientService.updateClient(clientDTO,identification));
    }

    @DeleteMapping("{identification}")
    public ResponseEntity<ClientBankDTO> deleteClientByIdentification(@PathVariable("identification") String identification) {
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(this.clientService.deleteClient(identification));
    }


}
