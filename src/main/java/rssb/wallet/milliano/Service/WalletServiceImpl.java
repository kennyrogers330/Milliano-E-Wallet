package rssb.wallet.milliano.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rssb.wallet.milliano.Repository.WalletRepository;
import rssb.wallet.milliano.RequestModels.CreateWalletRequest;
import rssb.wallet.milliano.model.Customer;
import rssb.wallet.milliano.model.Wallet;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation class for managing Wallet entities.
 * Implements methods for saving a wallet, retrieving a wallet by ID,
 * retrieving all wallets, and updating a wallet.
 */
@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private CustomerService customerService;

    /**
     * Saves a wallet with the provided request and customer ID.
     * @param walletRequest The request containing wallet details.
     * @param customerId The ID of the customer associated with the wallet.
     * @return The saved wallet.
     */
    @Override
    @Transactional
    public Wallet save(CreateWalletRequest walletRequest, Long customerId) {
        Customer existingCustomer = customerService.findById(customerId);
        Wallet newWallet = new Wallet(walletRequest.getBalance());
        existingCustomer.addWallet(newWallet);
        return walletRepository.save(newWallet);
    }

    /**
     * Retrieves a wallet by its unique ID.
     * @param id The ID of the wallet to retrieve.
     * @return The wallet with the specified ID, or null if not found.
     */
    @Override
    public Wallet findById(Long id) {
        Optional<Wallet> wallet = walletRepository.findById(id);
        Wallet theWallet = null;
        if (wallet.isPresent()) {
            theWallet = wallet.get();
        }
        return theWallet;
    }

    /**
     * Retrieves all wallets.
     * @return A list of all wallets.
     */
    @Override
    public List<Wallet> findAllWallets() {
        return walletRepository.findAll();
    }

    /**
     * Updates the details of a wallet.
     * @param tempWallet The wallet object with updated details.
     */
    @Override
    public void patch(Wallet tempWallet) {
        walletRepository.save(tempWallet);
    }
}
