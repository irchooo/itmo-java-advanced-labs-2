package ru.itmo.spring_database_jpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.spring_database_jpa.dto.ClientDTO;
import ru.itmo.spring_database_jpa.mapper.ClientMapper;
import ru.itmo.spring_database_jpa.model.Client;
import ru.itmo.spring_database_jpa.model.Tariff;
import ru.itmo.spring_database_jpa.repository.ClientRepository;
import ru.itmo.spring_database_jpa.repository.TariffRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final TariffRepository tariffRepository;
    private final ClientMapper clientMapper;


    public List<ClientDTO> findAll() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ClientDTO> findById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::toDto);
    }

    public ClientDTO create(ClientDTO clientDTO) {
        if (clientRepository.existsByEmail(clientDTO.getEmail())) {
            throw new RuntimeException("Client with this email already exists");
        }

        Client client = clientMapper.toEntity(clientDTO);

        if (clientDTO.getTariffId() != null) {
            Tariff tariff = tariffRepository.findById(clientDTO.getTariffId())
                    .orElseThrow(() -> new RuntimeException("Tariff not found"));
            client.setTariff(tariff);
        }

        Client saved = clientRepository.save(client);
        return clientMapper.toDto(saved);
    }

    public ClientDTO update(Long id, ClientDTO clientDTO) {
        Client existing = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        clientMapper.updateEntityFromDto(clientDTO, existing);

        if (clientDTO.getTariffId() != null) {
            Tariff tariff = tariffRepository.findById(clientDTO.getTariffId())
                    .orElseThrow(() -> new RuntimeException("Tariff not found"));
            existing.setTariff(tariff);
        }

        Client updated = clientRepository.save(existing);
        return clientMapper.toDto(updated);
    }

    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client not found");
        }
        clientRepository.deleteById(id);
    }
}
