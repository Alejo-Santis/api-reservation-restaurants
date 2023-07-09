package co.alejosatdev.apireservationrestaurants.services;

import co.alejosatdev.apireservationrestaurants.entities.Customer;
import co.alejosatdev.apireservationrestaurants.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + id));
    }

    public Customer update(Long id, Customer customerUpdated) {
        Customer customerExist = findById(id);
        customerExist.setName(customerUpdated.getName());
        customerExist.setLastName(customerUpdated.getLastName());
        customerExist.setNumberPhone(customerUpdated.getNumberPhone());

        return customerRepository.save(customerExist);
    }

    public void deleteById(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("No customer with the ID was found: " + id);
        }
        customerRepository.deleteById(id);
    }
}
