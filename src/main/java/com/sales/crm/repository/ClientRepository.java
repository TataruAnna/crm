package com.sales.crm.repository;

import com.sales.crm.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findClientByName(String name);

}
