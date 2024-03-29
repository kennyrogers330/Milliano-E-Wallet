package rssb.wallet.milliano.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transaction")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private double amount;
    private LocalDateTime timestamp;

    private String type;
    private String currency;
    private String description;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "transactions_wallets",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "wallet_id"))
//    @JsonManagedReference
    private List<Wallet> wallets;

    public void addWallet(Wallet wallet) {

        if(wallets == null) {
            wallets = new ArrayList<>();
        }
        wallets.add(wallet);
    }
}

