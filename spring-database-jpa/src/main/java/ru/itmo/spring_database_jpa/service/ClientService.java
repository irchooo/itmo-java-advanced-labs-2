package ru.itmo.spring_database_jpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.spring_database_jpa.dto.ClientDto;
import ru.itmo.spring_database_jpa.mapper.ClientMapper;
import ru.itmo.spring_database_jpa.model.Client;
import ru.itmo.spring_database_jpa.model.Tariff;
import ru.itmo.spring_database_jpa.repository.ClientRepository;
import ru.itmo.spring_database_jpa.repository.TariffRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final TariffRepository tariffRepository;
    private final ClientMapper clientMapper;


    @Transactional(readOnly = true)
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public ClientDto findById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Client not found with id " + id));
    }

    @Transactional
    public ClientDto create(ClientDto clientDto) {
        if (clientRepository.existsByEmail(clientDto.getEmail())) {
            throw new RuntimeException("Client with this email already exists");
        }

        Client client = clientMapper.toEntity(clientDto);

        if (clientDto.getTariffId() != null) {
            Tariff tariff = tariffRepository.findById(clientDto.getTariffId())
                    .orElseThrow(() -> new RuntimeException("Tariff not found"));
            client.setTariff(tariff);
        }

        Client saved = clientRepository.save(client);
        return clientMapper.toDto(saved);
    }

    @Transactional
    public ClientDto update(Long id, ClientDto clientDTO) {
        Client existing = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id " + id));

        clientMapper.updateEntityFromDto(clientDTO, existing);

        if (clientDTO.getTariffId() != null) {
            Tariff tariff = tariffRepository.findById(clientDTO.getTariffId())
                    .orElseThrow(() -> new RuntimeException("Tariff not found"));
            existing.setTariff(tariff);
        }

        Client updated = clientRepository.save(existing);
        return clientMapper.toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client not found with id " + id);
        }
        clientRepository.deleteById(id);
    }
}
