package rssb.wallet.milliano.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rssb.wallet.milliano.model.Customer;

/**
 * Repository interface for managing Customer entities.
 * This interface extends JpaRepository, providing CRUD operations for the Customer entity
 * with a primary key of type Long.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
