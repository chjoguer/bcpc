package com.bcpc.customer.repository.dao;

import com.bcpc.customer.domain.Client;
import com.bcpc.customer.domain.Client2;

import java.util.List;
import java.util.Optional;

public interface IClientDAO {

    List<Client2> findClients();
    Optional<Client2> findClientById(String identification);
    Client createClient(Client person);
    Client2 createClient2(Client2 person);
    Client2 updateClient(Client2 person);
    Client2  deleteClient(Client2 person);

}
