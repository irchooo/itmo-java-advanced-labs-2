package ru.itmo.spring_database_jpa.mapper;

import org.springframework.stereotype.Component;
import ru.itmo.spring_database_jpa.dto.ClientDTO;
import ru.itmo.spring_database_jpa.model.Client;

@Component
public class ClientMapper {

    public ClientDTO toDto(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .phone(client.getPhone())
                .tariffId(client.getTariff() != null ? client.getTariff().getId() : null)
                .tariffName(client.getTariff() != null ? client.getTariff().getName() : null)
                .createdAt(client.getCreatedAt())
                .build();
    }

    public Client toEntity(ClientDTO clientDTO) {
        return Client.builder()
                .id(clientDTO.getId())
                .name(clientDTO.getName())
                .email(clientDTO.getEmail())
                .phone(clientDTO.getPhone())
                .build();
    }

    public void updateEntityFromDto(ClientDTO clientDTO, Client client) {
        if (clientDTO.getName() != null) {
            client.setName(clientDTO.getName());
        }
        if (clientDTO.getEmail() != null) {
            client.setEmail(clientDTO.getEmail());
        }
        if (clientDTO.getPhone() != null) {
            client.setPhone(clientDTO.getPhone());
        }
    }
}
