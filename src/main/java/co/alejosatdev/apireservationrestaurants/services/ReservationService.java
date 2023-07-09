package co.alejosatdev.apireservationrestaurants.services;

import co.alejosatdev.apireservationrestaurants.entities.Customer;
import co.alejosatdev.apireservationrestaurants.entities.Reservation;
import co.alejosatdev.apireservationrestaurants.entities.RestaurantTable;
import co.alejosatdev.apireservationrestaurants.repositories.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RestaurantTableService restaurantTableService;
    private final CustomerService customerService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, RestaurantTableService restaurantTableService, CustomerService customerService) {
        this.reservationRepository = reservationRepository;
        this.restaurantTableService = restaurantTableService;
        this.customerService = customerService;
    }

    public Reservation save(Reservation reservation) {
        RestaurantTable table = restaurantTableService.findById(reservation.getRestaurantTable().getId());
        Customer customer = customerService.findById(reservation.getCustomer().getId());
        reservation.setRestaurantTable(table);
        reservation.setCustomer(customer);
        return reservationRepository.save(reservation);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public List<Reservation> findReservation(Long idCustomer, LocalDate date) {
        Customer customer = customerService.findById(idCustomer);
        return reservationRepository.findByCustomerAndDate(customer, date);
    }

    public Reservation findById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No reservation found with the ID: " + id));
    }

    public Reservation update(Long id, Reservation reservationUpdated) {
        Reservation reservationExist = findById(id);
        reservationExist.setDate(reservationUpdated.getDate());
        reservationExist.setTime(reservationUpdated.getTime());
        reservationExist.setNumberPersons(reservationUpdated.getNumberPersons());

        return reservationRepository.save(reservationExist);
    }

    public void deleteById(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new EntityNotFoundException("No reservation found with the ID:" + id);
        }
        reservationRepository.deleteById(id);
    }
}
