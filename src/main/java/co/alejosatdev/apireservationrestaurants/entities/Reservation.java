package co.alejosatdev.apireservationrestaurants.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservations")
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @NotNull(message = "The customer's reservation cannot be null and void")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "restaurant_table_id")
    @NotNull(message = "The reserve table cannot be null and void")
    private RestaurantTable restaurantTable;

    @NotNull(message = "The reservation date cannot be null and void")
    private LocalDate date;
    @NotNull(message = "The time of the reservation cannot be null and void")
    private LocalTime time;
    @NotNull(message = "The number of people in the reserve cannot be zero")
    @Min(value = 1, message = "The number of persons must be greater than or equal to 1")
    private  Integer numberPersons;
}
