package az.wallet.mcuserwallet.dto.response;

import az.wallet.mcuserwallet.domain.enums.WalletCurrency;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class WalletTopUpResponse {
    private UUID userId;
    private UUID walletId;
    private UUID transactionId;
    private LocalDateTime createdAt;
    private BigDecimal balance;
    private WalletCurrency currency;
    private String provider;
}
