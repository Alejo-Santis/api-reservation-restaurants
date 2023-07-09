package co.alejosatdev.apireservationrestaurants.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "customers")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "The customer's name cannot be empty")
    private String name;
    @NotBlank(message = "The customer's lastname cannot be empty")
    private String lastName;
    @NotBlank(message = "The customer's phone cannot be empty")
    private String numberPhone;
}
