package co.alejosatdev.apireservationrestaurants.repositories;

import co.alejosatdev.apireservationrestaurants.entities.Customer;
import co.alejosatdev.apireservationrestaurants.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCustomerAndDate(Customer customer, LocalDate date);
}
