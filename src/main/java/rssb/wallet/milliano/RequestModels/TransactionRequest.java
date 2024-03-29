package rssb.wallet.milliano.RequestModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionRequest {
    @NotBlank(message = "Transaction Type is required")
    @JsonProperty("transactionType")
    private String transactionType;

    @NotNull(message = "Amount is required")
    @JsonProperty("amount")
    private Double amount;

    @NotBlank(message = "Currency is required")
    @JsonProperty("currency")
    private String currency;

    @NotBlank(message = "Description is required")
    @JsonProperty("description")
    private String description;

    @NotNull(message = "Timestamp is required")
    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    @NotNull(message = "At least one Wallet is required")
    @JsonProperty("wallets")
    private WalletTransaction[] wallets;

}
