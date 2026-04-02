package az.wallet.mcuserwallet.dto.response;

import az.wallet.mcuserwallet.domain.enums.WalletCurrency;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class WalletTopUpResponse {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;
    private UUID userId;
    private BigDecimal balance;
    private WalletCurrency currency;

}
