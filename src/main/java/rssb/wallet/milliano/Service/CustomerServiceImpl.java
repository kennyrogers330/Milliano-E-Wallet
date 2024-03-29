package rssb.wallet.milliano.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rssb.wallet.milliano.Repository.CustomerRepository;
import rssb.wallet.milliano.model.Customer;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation class for managing Customer entities.
 * Implements methods for retrieving, saving, and retrieving all Customer entities.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Retrieves a customer by their unique ID.
     * @param id The ID of the customer to retrieve.
     * @return The customer with the specified ID, or null if not found.
     */
    @Override
    public Customer findById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        Customer theCustomer = null;

        if (customer.isPresent()) {
            theCustomer = customer.get();
        }
        return theCustomer;
    }

    /**
     * Retrieves all customers.
     * @return A list of all customers.
     */
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    /**
     * Saves a customer.
     * @param customer The customer to save.
     * @return The saved customer.
     */
    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
