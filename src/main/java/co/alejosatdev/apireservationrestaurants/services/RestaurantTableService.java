package co.alejosatdev.apireservationrestaurants.services;

import co.alejosatdev.apireservationrestaurants.entities.RestaurantTable;
import co.alejosatdev.apireservationrestaurants.repositories.RestaurantTableRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantTableService {
    private final RestaurantTableRepository tableRepository;

    @Autowired
    public RestaurantTableService(RestaurantTableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public RestaurantTable save(RestaurantTable table) {
        return tableRepository.save(table);
    }

    public RestaurantTable update(Long id, RestaurantTable updateTable) {
        RestaurantTable tableExist = findById(id);
        tableExist.setNumber(updateTable.getNumber());
        tableExist.setCapacity(updateTable.getCapacity());
        tableExist.setLocation(updateTable.getLocation());
        tableExist.setAvailable(updateTable.isAvailable());

        return tableRepository.save(tableExist);
    }

    public List<RestaurantTable> getAvailableTables() {
        return tableRepository.findByAvailable(true);
    }

    public List<RestaurantTable> findByLocationAndCapacity(String location, int capacity) {
        return tableRepository.findByLocationAndCapacityGreaterThanEqual(location, capacity);
    }

    public RestaurantTable findById(Long id) {
        return tableRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Table not found with ID: " + id));
    }

    public void deleteById(Long id) {
        if (!tableRepository.existsById(id)) {
            throw  new EntityNotFoundException("The table with the ID was not found: " + id);
        }
        tableRepository.deleteById(id);
    }
}
