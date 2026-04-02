package az.wallet.mcuserwallet.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class WalletTopUpRequest {
    @NotNull
    @DecimalMin(value = "1.00", message = "Amount should be greater than 1.00")
    private BigDecimal amount;
}
