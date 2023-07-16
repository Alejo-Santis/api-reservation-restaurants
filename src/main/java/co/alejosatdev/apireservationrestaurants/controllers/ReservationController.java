package co.alejosatdev.apireservationrestaurants.controllers;

import co.alejosatdev.apireservationrestaurants.entities.Reservation;
import co.alejosatdev.apireservationrestaurants.services.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservation() {
        List<Reservation> reservation = reservationService.findAll();
        return ResponseEntity.ok(reservation);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Long id) {
        Reservation reservation = reservationService.findById(id);
        return ResponseEntity.ok(reservation);
    }
    @PostMapping
    public ResponseEntity<Reservation> saveReservation(@Valid @RequestBody Reservation reservation) {
        Reservation reservationCreated = reservationService.save(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationCreated);
    }
    @GetMapping(params = {"customer", "date"})
    public ResponseEntity<List<Reservation>> findReservation(@RequestParam Long customerId, @RequestParam String date) {
        LocalDate dateReservation = LocalDate.parse(date);
        List<Reservation> reservations = reservationService.findReservation(customerId, dateReservation);
        return ResponseEntity.ok(reservations);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @Valid @RequestBody Reservation reservation) {
        Reservation reservationUpdated = reservationService.update(id, reservation);
        return ResponseEntity.ok(reservationUpdated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
