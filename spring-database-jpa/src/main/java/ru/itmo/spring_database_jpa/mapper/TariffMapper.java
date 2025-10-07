package ru.itmo.spring_database_jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.itmo.spring_database_jpa.dto.TariffDto;
import ru.itmo.spring_database_jpa.model.Tariff;

@Mapper(componentModel = "spring")
public interface TariffMapper {

    TariffDto toDto(Tariff tariff);

    Tariff toEntity(TariffDto tariffDto);

    void updateEntityFromDto(TariffDto tariffDto, @MappingTarget Tariff tariff);
}
