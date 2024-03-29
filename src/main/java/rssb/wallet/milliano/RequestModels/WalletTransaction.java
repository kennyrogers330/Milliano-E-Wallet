package rssb.wallet.milliano.RequestModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WalletTransaction {
    @JsonProperty("walletId")
    @NotNull(message = "Wallet ID is required")
    private Long walletId;

    @NotNull(message = "Transaction amount is required")
    @JsonProperty("amount")
    private double amount;
}
