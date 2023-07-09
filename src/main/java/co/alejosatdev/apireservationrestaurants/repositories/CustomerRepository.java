package co.alejosatdev.apireservationrestaurants.repositories;

import co.alejosatdev.apireservationrestaurants.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
