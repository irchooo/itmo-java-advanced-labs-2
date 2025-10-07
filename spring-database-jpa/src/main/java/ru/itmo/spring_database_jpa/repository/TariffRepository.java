package ru.itmo.spring_database_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.spring_database_jpa.model.Tariff;

import java.util.List;
import java.util.Optional;

public interface TariffRepository extends JpaRepository<Tariff, Long> {
    Optional<Tariff> findByName(String name);
    List<Tariff> findByIsActiveTrue();
}
