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
import rssb.wallet.milliano.RequestModels.CreateWalletRequest;
import rssb.wallet.milliano.Service.CustomerService;
import rssb.wallet.milliano.Service.WalletService;
import rssb.wallet.milliano.model.Customer;
import rssb.wallet.milliano.model.Wallet;

import java.util.List;

@RestController
@RequestMapping("/wallets")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Create a wallet for a customer", description = "Create a wallet for a customer with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Wallet created successfully", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad request - Validation errors", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "text/plain"))
    })
    @PostMapping("/createWallet")
    public  ResponseEntity<Object>  createWallet(@Validated @RequestBody CreateWalletRequest walletRequest, BindingResult result) {
    try{
        if (result.hasErrors()) {
                System.out.println(walletRequest.toString());
                // If validation errors exist, return the list of validation errors
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }

        Customer customer = customerService.findById(walletRequest.getCustomerId());
        // Check if the customer exists
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }

        Wallet savedWallet = walletService.save(walletRequest, walletRequest.getCustomerId());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedWallet);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating wallet");
        }
    }

    @Operation(summary = "Get wallet by ID", description = "Retrieve wallet details by its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wallet details retrieved successfully", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Wallet not found", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/wallet/{walletId}")
    public ResponseEntity<Object> getWalletById(@PathVariable Long walletId) {
        try{
            Wallet wallet = walletService.findById(walletId);
            if(wallet != null){
                return ResponseEntity.ok(wallet);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wallet not found");
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching wallet: " + e.getMessage());
        }
    }

    @Operation(summary = "Get all wallets", description = "Retrieve details of all wallets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wallets retrieved successfully", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "No wallets found", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/")
    public ResponseEntity<Object> getAllWallets() {
        try{
            List<Wallet> walletList = walletService.findAllWallets();
            if(!walletList.isEmpty()) {
                return ResponseEntity.ok(walletList);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Wallets found");
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching wallets: " + e.getMessage());
        }
    }

}
