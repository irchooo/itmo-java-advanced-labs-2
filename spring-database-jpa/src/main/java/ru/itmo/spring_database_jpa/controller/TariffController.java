package ru.itmo.spring_database_jpa.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import ru.itmo.spring_database_jpa.dto.TariffDto;
import ru.itmo.spring_database_jpa.service.TariffService;

import java.util.List;

@RestController
@RequestMapping("/api/tariffs")
@RequiredArgsConstructor
public class TariffController {
    private final TariffService tariffService;

    @GetMapping
    public List<TariffDto> getAllTariffs() {
        return tariffService.findAll();
    }

    @GetMapping("/active")
    public List<TariffDto> getActiveTariffs() {
        return tariffService.findActiveTariffs();
    }

    @GetMapping("/{id}")
    public TariffDto getTariffById(@PathVariable Long id) {
        return tariffService.findById(id).orElse(null);
    }

    @GetMapping("/name/{name}")
    public TariffDto getTariffByName(@PathVariable String name) {
        return tariffService.findByName(name).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TariffDto createTariff(@Valid @RequestBody TariffDto tariffDto) {
        return tariffService.create(tariffDto);
    }

    @PutMapping("/{id}")
    public TariffDto updateTariff(@PathVariable Long id, @Valid @RequestBody TariffDto tariffDto) {
        return tariffService.update(id, tariffDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTariff(@PathVariable Long id) {
        tariffService.delete(id);
    }

    @PostMapping("/{id}/activate")
    public TariffDto activateTariff(@PathVariable Long id) {
        return tariffService.activateTariff(id);
    }

    @PostMapping("/{id}/deactivate")
    public TariffDto deactivateTariff(@PathVariable Long id) {
        return tariffService.deactivateTariff(id);
    }
}
