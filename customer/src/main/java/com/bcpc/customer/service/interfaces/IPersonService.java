package com.bcpc.customer.service.interfaces;

import com.bcpc.customer.controller.dto.ClientBankDTO;
import com.bcpc.customer.controller.dto.ClientDTO;

import java.util.List;

public interface IPersonService {

    List<ClientBankDTO> findAll();
    ClientBankDTO findById(String identification);
    ClientBankDTO createClient(ClientBankDTO person);
    ClientBankDTO updateClient(ClientBankDTO person,String identification);
    ClientBankDTO deleteClient(String identification);


}
