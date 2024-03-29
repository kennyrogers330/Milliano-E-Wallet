package rssb.wallet.milliano.Service;

import rssb.wallet.milliano.RequestModels.CreateWalletRequest;
import rssb.wallet.milliano.model.Wallet;

import java.util.List;

/**
 * Service interface for managing Wallet entities.
 * Defines methods for saving a wallet, retrieving a wallet by ID,
 * retrieving all wallets, and updating a wallet.
 */
public interface WalletService {
    /**
     * Saves a wallet with the provided request and customer ID.
     * @param walletRequest The request containing wallet details.
     * @param customerId The ID of the customer associated with the wallet.
     * @return The saved wallet.
     */
    Wallet save(CreateWalletRequest walletRequest, Long customerId);
    /**
     * Retrieves a wallet by its unique ID.
     * @param id The ID of the wallet to retrieve.
     * @return The wallet with the specified ID, or null if not found.
     */
    Wallet findById(Long id);

    /**
     * Retrieves all wallets.
     * @return A list of all wallets.
     */
    List<Wallet> findAllWallets();

    /**
     * Updates the details of a wallet.
     * @param tempWallet The wallet object with updated details.
     */
    void patch(Wallet tempWallet);
}
