package rssb.wallet.milliano.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rssb.wallet.milliano.model.Wallet;

/**
 * Repository interface for managing Wallet entities.
 * This interface extends JpaRepository, providing CRUD operations for the Wallet entity
 * with a primary key of type Long.
 */
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
