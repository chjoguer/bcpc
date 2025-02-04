package com.bcpc.customer.repository.implementation;

import com.bcpc.customer.domain.Client;
import com.bcpc.customer.repository.dao.IClientDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientDAOImpl implements IClientDAO {


    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Client> findClients() {
        return this.em.createQuery("SELECT c FROM Client c", Client.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> findClientById(String identification) {
        return this.em.createQuery(
                        "SELECT c FROM Client c WHERE c.identification = :identification", Client.class)
                .setParameter("identification", identification)
                .getResultStream()
                .findFirst();

//      return Optional.ofNullable(this.em.find(Client.class, id));
    }

    @Override
    @Transactional
    public Client createClient(Client person) {
        this.em.persist(person);
        this.em.flush();
        return person;

    }





    @Override
    @Transactional
    public Client updateClient(Client person) {
        this.em.merge(person);
        return person;
    }

    @Override
    @Transactional
    public Client deleteClient(Client person) {
        this.em.remove(person);
        return person;
    }
}
