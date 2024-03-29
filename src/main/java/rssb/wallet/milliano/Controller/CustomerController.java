package rssb.wallet.milliano.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rssb.wallet.milliano.Repository.CustomerRepository;
import rssb.wallet.milliano.model.Customer;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @Operation(summary = "Create a new customer", description = "Create a new customer record with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer created successfully", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad request - Validation errors or password mismatch", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "text/plain"))
    })
    @PostMapping("/create")
    public ResponseEntity<Object>  saveCustomer(@Validated @RequestBody Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(">>>>>>>>>>>>>>>>" + customer.toString());
            // If validation errors exist, return the list of validation errors
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }

        // Check if password and confirm password match
        if (!customer.getPassword().equals(customer.getConfirmPassword())) {
            // If passwords don't match, return a custom error message
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password and confirm password do not match");
        }

        try {
            Customer savedCustomer = customerRepository.save(customer);
            return ResponseEntity.status(201).body(savedCustomer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while saving customer");
        }
    }

    @Operation(summary = "Get customer by ID", description = "Retrieve customer details by their unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer details retrieved successfully", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long customerId) {
        try {
        // Retrieve customer from the repository
        Customer customer = customerRepository.findById(customerId).orElse(null);
        // Check if customer exists
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching customer");
        }
    }

    @Operation(summary = "Get all customers", description = "Retrieve details of all customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customers retrieved successfully", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "No customers found", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/")
    public ResponseEntity<Object> getAllCustomers() {
        try {
            // Retrieve all customers from the repository
            List<Customer> customers = customerRepository.findAll();

            // Check if customers exist
            if (!customers.isEmpty()) {
                return ResponseEntity.ok(customers);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No customers found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching customers");
        }
    }
}
