package com.bcpc.customer.repository.implementation;

import com.bcpc.customer.domain.Client;
import com.bcpc.customer.domain.Client2;
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
    public List<Client2> findClients() {
        return this.em.createQuery("SELECT c FROM Client2 c", Client2.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client2> findClientById(String identification) {
        return this.em.createQuery(
                        "SELECT c FROM Client2 c WHERE c.identification = :identification", Client2.class)
                .setParameter("identification", identification)
                .getResultStream()
                .findFirst();

//      return Optional.ofNullable(this.em.find(Client2.class, id));
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
    public Client2 createClient2(Client2 client2Entity) {
        System.out.println("Client2 created"+client2Entity);
        this.em.persist(client2Entity);
        this.em.flush();
        return client2Entity;
    }



    @Override
    @Transactional
    public Client2 updateClient(Client2 person) {
        this.em.merge(person);
        return person;
    }

    @Override
    @Transactional
    public Client2 deleteClient(Client2 person) {
        this.em.remove(person);
        return person;
    }
}
