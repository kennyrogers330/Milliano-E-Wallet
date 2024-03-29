package rssb.wallet.milliano.Service;

import rssb.wallet.milliano.model.Customer;

import java.util.List;

/**
 * Service interface for managing Customer entities.
 * Defines methods for retrieving, saving, and retrieving all Customer entities.
 */
public interface CustomerService {

    /**
     * Retrieves a customer by their unique ID.
     * @param id The ID of the customer to retrieve.
     * @return The customer with the specified ID, or null if not found.
     */
    Customer findById(Long id);

    /**
     * Retrieves all customers.
     * @return A list of all customers.
     */
    List<Customer> findAll();

    /**
     * Saves a customer.
     * @param customer The customer to save.
     * @return The saved customer.
     */
    Customer save(Customer customer);
}
