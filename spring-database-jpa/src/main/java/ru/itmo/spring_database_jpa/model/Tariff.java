package ru.itmo.spring_database_jpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tariffs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Column(unique = true, nullable = false)
    private String name;

    @Size(max = 255, message = "Description too long")
    private String description;

    @DecimalMin(value = "0.0", message = "Price cannot be negative")
    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Boolean isActive = true;

    // Связь Многие к Одному: многие клиенты могут иметь один тариф
    @OneToMany(mappedBy = "tariff", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Client> clients = new ArrayList<>();
}
