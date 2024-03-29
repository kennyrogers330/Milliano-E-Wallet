package rssb.wallet.milliano.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rssb.wallet.milliano.RequestModels.TransactionRequest;
import rssb.wallet.milliano.RequestModels.WalletTransaction;
import rssb.wallet.milliano.Service.CustomerService;
import rssb.wallet.milliano.Service.TransactionService;
import rssb.wallet.milliano.Service.WalletService;
import rssb.wallet.milliano.model.Customer;
import rssb.wallet.milliano.model.Transaction;
import rssb.wallet.milliano.model.Wallet;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private WalletService walletService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TransactionService transactionService;


    @Operation(summary = "Perform a transaction", description = "Perform a financial transaction with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction performed successfully", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad request, validation errors", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Not Found, wallet not found", content = {@Content(mediaType = "text/plain")}),
            @ApiResponse(responseCode = "403", description = "Forbidden, insufficient funds", content = {@Content(mediaType = "text/plain")}),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {@Content(mediaType = "text/plain")})
    })
    @PostMapping("/performTransaction")
    public ResponseEntity<Object> performTransaction(@Validated @RequestBody TransactionRequest transactionRequest, BindingResult result) {
        try{
            if (result.hasErrors()) {
                System.out.println(transactionRequest.toString());
                // If validation errors exist, return the list of validation errors
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
            }
            Transaction transaction = new Transaction();
            for (WalletTransaction wallet : transactionRequest.getWallets()) {
                Wallet tempWallet = walletService.findById(wallet.getWalletId());
                if(tempWallet == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A Wallet involved in this transaction was not found");
                }
                double newBalance = tempWallet.getBalance() + wallet.getAmount();

                if(newBalance < 0) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You don't have enough funds for this transaction");
                }
                tempWallet.setBalance(newBalance);
                walletService.patch(tempWallet);
                transaction.addWallet(tempWallet);
            }

            transaction.setAmount(transactionRequest.getAmount());
            transaction.setType(transactionRequest.getTransactionType());
            transaction.setDescription(transactionRequest.getDescription());
            transaction.setCurrency(transactionRequest.getCurrency());
            transaction.setTimestamp(transactionRequest.getTimestamp());
            Transaction savedTransaction = transactionService.save(transaction);

            return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.findById(savedTransaction.getId()));
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating wallet");
        }
    }
    @Operation(summary = "Get transaction by ID", description = "Retrieve details of a transaction by its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction details retrieved successfully", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Transaction not found", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/transaction/{id}")
    public ResponseEntity<Object> getTransactionById(@PathVariable Long id) {
        try{
            Transaction transaction = transactionService.findById(id);
            if(transaction != null) {
                return  ResponseEntity.ok().body(transaction);
            } else {
              return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction Not Found");
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching the transaction: " + e.getMessage());
        }
    }

    @Operation(summary = "Get all transactions", description = "Retrieve details of all transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transactions retrieved successfully", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "No transactions found", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/")
    public ResponseEntity<Object> getAllTransactions() {
        try{
            List<Transaction> transactions = transactionService.findAllTransactions();
            if(!transactions.isEmpty()) {
                return  ResponseEntity.ok().body(transactions);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Transactions found");
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching wallets: " + e.getMessage());
        }
    }

}
