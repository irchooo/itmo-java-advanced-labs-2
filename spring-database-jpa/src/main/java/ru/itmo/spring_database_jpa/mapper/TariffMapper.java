package ru.itmo.spring_database_jpa.mapper;

import org.springframework.stereotype.Component;
import ru.itmo.spring_database_jpa.dto.TariffDTO;
import ru.itmo.spring_database_jpa.model.Tariff;

@Component
public class TariffMapper {

    public TariffDTO toDto(Tariff tariff) {
        if (tariff == null) {
            return null;
        }

        return TariffDTO.builder()
                .id(tariff.getId())
                .name(tariff.getName())
                .description(tariff.getDescription())
                .price(tariff.getPrice())
                .isActive(tariff.getIsActive())
                .build();
    }

    public Tariff toEntity(TariffDTO tariffDTO) {
        if (tariffDTO == null) {
            return null;
        }

        return Tariff.builder()
                .id(tariffDTO.getId())
                .name(tariffDTO.getName())
                .description(tariffDTO.getDescription())
                .price(tariffDTO.getPrice())
                .isActive(tariffDTO.getIsActive() != null ? tariffDTO.getIsActive() : true)
                .build();
    }

    public void updateEntityFromDto(TariffDTO tariffDTO, Tariff tariff) {
        if (tariffDTO == null || tariff == null) {
            return;
        }

        if (tariffDTO.getName() != null) {
            tariff.setName(tariffDTO.getName());
        }
        if (tariffDTO.getDescription() != null) {
            tariff.setDescription(tariffDTO.getDescription());
        }
        if (tariffDTO.getPrice() != null) {
            tariff.setPrice(tariffDTO.getPrice());
        }
        if (tariffDTO.getIsActive() != null) {
            tariff.setIsActive(tariffDTO.getIsActive());
        }
    }
}
