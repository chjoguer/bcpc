package com.bcpc.customer.service.implmentations;

import com.bcpc.customer.controller.dto.ClientBankDTO;
import com.bcpc.customer.controller.dto.ClientDTO;
import com.bcpc.customer.domain.Client;
import com.bcpc.customer.repository.ClientRepository;
import com.bcpc.customer.repository.dao.IClientDAO;
import com.bcpc.customer.service.interfaces.IPersonService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final BCryptPasswordEncoder passwordEncoder;

    public ClientServiceImpl() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


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
        Optional<Client> userEntity = this.clientDAO.findClientById(identification);

        if (userEntity.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            Client currentClient = userEntity.get();
            return modelMapper.map(currentClient, ClientBankDTO.class);
        }else{
            return null;
        }

    }


    @Override
    public ClientBankDTO createClient(ClientBankDTO clientBankDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();



            TypeMap<ClientBankDTO, Client> typeMap = modelMapper.createTypeMap(ClientBankDTO.class, Client.class);

            typeMap.addMappings(mapper -> {
                mapper.map(ClientBankDTO::getName, Client::setName);
                mapper.map(ClientBankDTO::getAge, Client::setAge);
                mapper.map(ClientBankDTO::getAddress, Client::setAddress);
            });

            Client newClient = modelMapper.map(clientBankDTO, Client.class);
            System.out.println("Mapped Client: " + newClient);
            newClient.setPassword(this.cryptPassword(newClient.getPassword()));
            this.clientDAO.createClient(newClient);
            return clientBankDTO;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new UnsupportedOperationException("Error al guardar el usuario2");
        }
    }



    @Override
    public ClientBankDTO updateClient(ClientBankDTO person, String identification) {

        Optional<Client> clientEntity = this.clientDAO.findClientById(identification);

        if (clientEntity.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();

            Client currentClient = clientEntity.get();
            currentClient.setName(person.getName());
            currentClient.setAge(person.getAge());
            currentClient.setPhone(person.getPhone());
            currentClient.setAddress(person.getAddress());
            currentClient.setStatus(person.getStatus());

            this.clientDAO.updateClient(currentClient);
            return modelMapper.map(currentClient, ClientBankDTO.class);
        }else {

            throw new IllegalArgumentException("El usuario no existe");
        }

    }

    @Override
    public ClientBankDTO  deleteClient(String identification) {

        Optional<Client> clientEntity = this.clientDAO.findClientById(identification);

        if (clientEntity.isPresent()) {

            ModelMapper modelMapper = new ModelMapper();

            Client currentClient = clientEntity.get();
            currentClient.setStatus(0);

            this.clientDAO.updateClient(currentClient);

            return modelMapper.map(currentClient, ClientBankDTO.class);
        }else {

            return new ClientBankDTO();
        }
    }

    public String cryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
