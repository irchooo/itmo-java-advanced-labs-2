package ru.itmo.spring_database_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.spring_database_jpa.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByEmail(String email);
}
