package az.wallet.mcuserwallet.domain;

import az.wallet.mcuserwallet.domain.enums.WalletCurrency;
import az.wallet.mcuserwallet.domain.enums.WalletStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Builder
@Table(name = "wallets")
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @Id
    @Column(name = "id",unique = true)
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private WalletCurrency walletCurrency;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private WalletStatus status;



    public void withdraw(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }

    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }
}
