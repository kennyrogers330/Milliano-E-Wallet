package rssb.wallet.milliano.RequestModels;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateWalletRequest {
    @NotNull(message = "Balance is required")
    private Double balance;

    @NotNull(message = "Customer ID is required")
    private Long customerId;
}
