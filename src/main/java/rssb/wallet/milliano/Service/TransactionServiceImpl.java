package rssb.wallet.milliano.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rssb.wallet.milliano.Repository.TransactionRepository;
import rssb.wallet.milliano.model.Transaction;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation class for managing Transaction entities.
 * Implements methods for saving, retrieving all transactions, and retrieving a transaction by ID.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Saves a transaction.
     * @param transaction The transaction to save.
     * @return The saved transaction.
     */
    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    /**
     * Retrieves all transactions.
     * @return A list of all transactions.
     */
    @Override
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    /**
     * Retrieves a transaction by its unique ID.
     * @param id The ID of the transaction to retrieve.
     * @return The transaction with the specified ID, or null if not found.
     */
    @Override
    public Transaction findById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        Transaction theTransaction = null;
        if (transaction.isPresent()) {
            theTransaction = transaction.get();
        }
        return theTransaction;
    }
}
