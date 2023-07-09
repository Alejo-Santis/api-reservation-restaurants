package co.alejosatdev.apireservationrestaurants.repositories;

import co.alejosatdev.apireservationrestaurants.entities.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantTableRepository  extends JpaRepository<RestaurantTable, Long> {
    List<RestaurantTable> findByAvailable(boolean avaliable);
    List<RestaurantTable> findByLocationAndCapacityGreaterThanEqual(String Location, int capacity);
}
