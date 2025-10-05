package ru.itmo.spring_database_jpa.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.spring_database_jpa.dto.TariffDTO;
import ru.itmo.spring_database_jpa.mapper.TariffMapper;
import ru.itmo.spring_database_jpa.model.Tariff;
import ru.itmo.spring_database_jpa.repository.TariffRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TariffService {

    private final TariffRepository tariffRepository;
    private final TariffMapper tariffMapper;


    @Transactional(readOnly = true)
    public List<TariffDTO> findAll() {
        return tariffRepository.findAll()
                .stream()
                .map(tariffMapper::toDto)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public Optional<TariffDTO> findById(Long id) {
        return tariffRepository.findById(id)
                .map(tariffMapper::toDto);
    }


    @Transactional(readOnly = true)
    public List<TariffDTO> findActiveTariffs() {
        return tariffRepository.findByIsActiveTrue()
                .stream()
                .map(tariffMapper::toDto)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public Optional<TariffDTO> findByName(String name) {
        return tariffRepository.findByName(name)
                .map(tariffMapper::toDto);
    }


    public TariffDTO create(TariffDTO tariffDTO) {

        if (tariffRepository.findByName(tariffDTO.getName()).isPresent()) {
            throw new RuntimeException("Tariff with name '" + tariffDTO.getName() + "' already exists");
        }

        Tariff tariff = tariffMapper.toEntity(tariffDTO);
        Tariff saved = tariffRepository.save(tariff);
        return tariffMapper.toDto(saved);
    }


    public TariffDTO update(Long id, TariffDTO tariffDTO) {
        Tariff existing = tariffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tariff not found with id: " + id));


        if (tariffDTO.getName() != null &&
                !existing.getName().equals(tariffDTO.getName()) &&
                tariffRepository.findByName(tariffDTO.getName()).isPresent()) {
            throw new RuntimeException("Tariff with name '" + tariffDTO.getName() + "' already exists");
        }

        tariffMapper.updateEntityFromDto(tariffDTO, existing);

        Tariff updated = tariffRepository.save(existing);
        return tariffMapper.toDto(updated);
    }


    public void delete(Long id) {
        if (!tariffRepository.existsById(id)) {
            throw new RuntimeException("Tariff not found with id: " + id);
        }
        tariffRepository.deleteById(id);
    }


    public TariffDTO activateTariff(Long id) {
        Tariff tariff = tariffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tariff not found with id: " + id));
        tariff.setIsActive(true);
        Tariff updated = tariffRepository.save(tariff);
        return tariffMapper.toDto(updated);
    }


    public TariffDTO deactivateTariff(Long id) {
        Tariff tariff = tariffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tariff not found with id: " + id));
        tariff.setIsActive(false);
        Tariff updated = tariffRepository.save(tariff);
        return tariffMapper.toDto(updated);
    }

}
