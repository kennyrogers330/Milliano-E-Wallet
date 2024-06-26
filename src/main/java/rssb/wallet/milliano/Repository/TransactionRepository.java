package rssb.wallet.milliano.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rssb.wallet.milliano.model.Transaction;

/**
 * Repository interface for managing Transaction entities.
 * This interface extends JpaRepository, providing CRUD operations for the Transaction entity
 * with a primary key of type Long.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
