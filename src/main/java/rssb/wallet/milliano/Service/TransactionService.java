package rssb.wallet.milliano.Service;

import rssb.wallet.milliano.model.Transaction;

import java.util.List;


/**
 * Service interface for managing Transaction entities.
 * Defines methods for saving, retrieving all transactions, and retrieving a transaction by ID.
 */
public interface TransactionService {
    /**
     * Saves a transaction.
     * @param transaction The transaction to save.
     * @return The saved transaction.
     */
    Transaction save(Transaction transaction);

    /**
     * Retrieves all transactions.
     * @return A list of all transactions.
     */
    List<Transaction> findAllTransactions();

    /**
     * Retrieves a transaction by its unique ID.
     * @param id The ID of the transaction to retrieve.
     * @return The transaction with the specified ID, or null if not found.
     */
    Transaction findById(Long id);
}
