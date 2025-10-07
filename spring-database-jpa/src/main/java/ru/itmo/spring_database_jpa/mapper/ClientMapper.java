package ru.itmo.spring_database_jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.itmo.spring_database_jpa.dto.ClientDto;
import ru.itmo.spring_database_jpa.model.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "tariffId", source = "tariff.id")
    @Mapping(target = "tariffName", source = "tariff.name")
    ClientDto toDto(Client client);

    @Mapping(target = "tariff", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Client toEntity(ClientDto clientDto);

    void updateEntityFromDto(ClientDto clientDto, @MappingTarget Client client);
}
