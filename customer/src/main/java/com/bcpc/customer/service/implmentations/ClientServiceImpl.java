package com.bcpc.customer.service.implmentations;

import com.bcpc.customer.controller.dto.ClientBankDTO;
import com.bcpc.customer.controller.dto.ClientDTO;
import com.bcpc.customer.domain.Client;
import com.bcpc.customer.domain.Client2;
import com.bcpc.customer.domain.Person;
import com.bcpc.customer.repository.ClientRepository;
import com.bcpc.customer.repository.dao.IClientDAO;
import com.bcpc.customer.service.interfaces.IPersonService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements IPersonService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private IClientDAO clientDAO;


    @Override
    public List<ClientBankDTO> findAll() {
        ModelMapper modelMapper = new ModelMapper();

        return this.clientDAO.findClients()
                .stream()
                .map(entity -> modelMapper.map(entity, ClientBankDTO.class))
                .collect(Collectors.toList());
    }



    @Override
    public ClientBankDTO findById(String identification) {
        Optional<Client2> userEntity = this.clientDAO.findClientById(identification);

        if (userEntity.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            Client2 currentClient = userEntity.get();
            return modelMapper.map(currentClient, ClientBankDTO.class);
        }else{
            return null;
        }

    }

    @Override
    public ClientDTO createClient(ClientDTO person) {

        try{
            ModelMapper modelMapper = new ModelMapper();
            Client newClient = modelMapper.map(person, Client.class);
//            System.out.println(newClient.getName());
            System.out.println("Object"+newClient);
            this.clientDAO.createClient(newClient);
            return person;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new UnsupportedOperationException("Error al guardar el usuario");
        }
    }

    @Override
    public ClientBankDTO createClient2(ClientBankDTO clientBankDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();



            TypeMap<ClientBankDTO, Client2> typeMap = modelMapper.createTypeMap(ClientBankDTO.class, Client2.class);
            typeMap.addMappings(mapper -> {
                mapper.map(ClientBankDTO::getName, Client2::setName);
                mapper.map(ClientBankDTO::getAge, Client2::setAge);
                mapper.map(ClientBankDTO::getAddress, Client2::setAddress);
            });

// Perform mapping
            Client2 newClient = modelMapper.map(clientBankDTO, Client2.class);
            System.out.println("Mapped Client2: " + newClient);

            this.clientDAO.createClient2(newClient);
            return clientBankDTO;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new UnsupportedOperationException("Error al guardar el usuario2");
        }
    }



    @Override
    public ClientBankDTO updateClient(ClientBankDTO person, String identification) {

        Optional<Client2> clientEntity = this.clientDAO.findClientById(identification);

        if (clientEntity.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();

            Client2 currentClient = clientEntity.get();
            currentClient.setName(person.getName());
            currentClient.setAge(person.getAge());
            currentClient.setPhone(person.getPhone());
            currentClient.setAddress(person.getAddress());

            this.clientDAO.updateClient(currentClient);
            return modelMapper.map(currentClient, ClientBankDTO.class);
        }else {

            throw new IllegalArgumentException("El usuario no existe");
        }

    }

    @Override
    public ClientBankDTO  deleteClient(String identification) {

        Optional<Client2> clientEntity = this.clientDAO.findClientById(identification);

        if (clientEntity.isPresent()) {

//            this.clientDAO.deleteClient(clientEntity.get());

            ModelMapper modelMapper = new ModelMapper();

            Client2 currentClient = clientEntity.get();
            currentClient.setStatus("INACTIVE");


            this.clientDAO.updateClient(currentClient);
            return modelMapper.map(currentClient, ClientBankDTO.class);
            //return "Usuario ha sido eliminado " + identification;

        }else {

            return new ClientBankDTO();
        }




    }
    // Add more methods for update, delete, find
}
