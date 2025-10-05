package ru.itmo.spring_database_jpa.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TariffDTO {
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Size(max = 255, message = "Description too long")
    private String description;

    @DecimalMin(value = "0.0", message = "Price cannot be negative")
    private BigDecimal price;

    private Boolean isActive;
}
