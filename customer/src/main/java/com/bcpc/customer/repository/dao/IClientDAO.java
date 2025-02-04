package com.bcpc.customer.repository.dao;

import com.bcpc.customer.domain.Client;

import java.util.List;
import java.util.Optional;

public interface IClientDAO {

    List<Client> findClients();
    Optional<Client> findClientById(String identification);
    Client createClient(Client person);
    Client updateClient(Client person);
    Client  deleteClient(Client person);

}
