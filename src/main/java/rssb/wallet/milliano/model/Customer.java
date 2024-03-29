package rssb.wallet.milliano.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be less than or equal to 50 characters")
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 50, message = "Middle name must be less than or equal to 50 characters")
    @Column(name = "middle_name")
    private String middleName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be less than or equal to 50 characters")
    @Column(name="last_name")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must be less than or equal to 100 characters")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Size(max = 20, message = "Phone number must be less than or equal to 20 characters")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotBlank(message = "National ID is required")
    @Size(max = 20, message = "National ID must be less than or equal to 20 characters")
    @Column(name = "national_id")
    private String nationalId;

    @NotBlank(message = "Password is required")
    @Size(min = 5, max = 20, message = "Password must be between 5 and 20 characters")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Confirm Password is required")
    @Transient // Exclude from persistence
    private String confirmPassword;

    @OneToMany(fetch=FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<Wallet> wallets;



    public Customer() {
    }

    public Customer(Long id, String firstName, String middleName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
    }

    public Customer(String firstName, String middleName, String lastName, String email) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
    }

    //Uni directional
        public void addWallet(Wallet newWallet) {
            if(wallets == null) {
                wallets = new ArrayList<>();
            }
            wallets.add(newWallet);
        }

}
