package co.alejosatdev.apireservationrestaurants.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "tables")
@Data
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "The table number cannot be null")
    private Integer number;
    @NotNull(message = "The capacity of the table cannot be null")
    @Min(value = 1, message = "The capacity of the table must be greater than or equal to 1")
    private Integer capacity;
    @NotBlank(message = "The location of the table cannot be empty")
    private String location;
    private boolean available;
}
