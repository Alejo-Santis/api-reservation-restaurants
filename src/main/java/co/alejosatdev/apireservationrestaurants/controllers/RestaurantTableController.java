package co.alejosatdev.apireservationrestaurants.controllers;

import co.alejosatdev.apireservationrestaurants.entities.RestaurantTable;
import co.alejosatdev.apireservationrestaurants.services.RestaurantTableService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/table")
public class RestaurantTableController {

    @Autowired
    private RestaurantTableService tableService;

    @GetMapping
    public ResponseEntity<List<RestaurantTable>> getTablesByLocationAndCapacity(
            @RequestParam String location,  @RequestParam int capacity) {
        List<RestaurantTable> tables = tableService.findByLocationAndCapacity(location, capacity);
        return ResponseEntity.ok(tables);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantTable> getTableById(@PathVariable Long id) {
        RestaurantTable table = tableService.findById(id);
        return ResponseEntity.ok(table);
    }
    @GetMapping("/available")
    public ResponseEntity<List<RestaurantTable>> getTablesAvailable() {
        List<RestaurantTable> tablesAvailables = tableService.getAvailableTables();
        return ResponseEntity.ok(tablesAvailables);
    }
    @PostMapping
    public ResponseEntity<RestaurantTable> saveTable(@Valid @RequestBody RestaurantTable restaurantTable) {
        RestaurantTable newTable = tableService.save(restaurantTable);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTable);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantTable> updateTable(@PathVariable Long id,
                                                       @Valid @RequestBody RestaurantTable restaurantTable) {
        RestaurantTable updateTable = tableService.update(id, restaurantTable);
        return ResponseEntity.ok(updateTable);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTableById(@PathVariable Long id) {
        tableService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
