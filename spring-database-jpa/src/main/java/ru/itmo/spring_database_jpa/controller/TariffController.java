package ru.itmo.spring_database_jpa.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itmo.spring_database_jpa.dto.TariffDTO;
import ru.itmo.spring_database_jpa.service.TariffService;

import java.util.List;

@RestController
@RequestMapping("/api/tariffs")
@RequiredArgsConstructor
public class TariffController {

    private final TariffService tariffService;

    @GetMapping
    public ResponseEntity<List<TariffDTO>> getAllTariffs() {
        List<TariffDTO> tariffs = tariffService.findAll();
        return ResponseEntity.ok(tariffs);
    }

    @GetMapping("/active")
    public ResponseEntity<List<TariffDTO>> getActiveTariffs() {
        List<TariffDTO> activeTariffs = tariffService.findActiveTariffs();
        return ResponseEntity.ok(activeTariffs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TariffDTO> getTariffById(@PathVariable Long id) {
        return tariffService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<TariffDTO> getTariffByName(@PathVariable String name) {
        return tariffService.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TariffDTO> createTariff(@Valid @RequestBody TariffDTO tariffDTO) {
        try {
            TariffDTO created = tariffService.create(tariffDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TariffDTO> updateTariff(
            @PathVariable Long id,
            @Valid @RequestBody TariffDTO tariffDTO) {
        try {
            TariffDTO updated = tariffService.update(id, tariffDTO);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTariff(@PathVariable Long id) {
        try {
            tariffService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<TariffDTO> activateTariff(@PathVariable Long id) {
        try {
            TariffDTO activated = tariffService.activateTariff(id);
            return ResponseEntity.ok(activated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<TariffDTO> deactivateTariff(@PathVariable Long id) {
        try {
            TariffDTO deactivated = tariffService.deactivateTariff(id);
            return ResponseEntity.ok(deactivated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
