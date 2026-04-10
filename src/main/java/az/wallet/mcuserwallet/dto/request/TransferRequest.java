package az.wallet.mcuserwallet.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.convert.DataSizeUnit;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class TransferRequest {
    @NotNull(message = "Sender id can not be null")
    private UUID senderId;

    @NotNull(message = "Receiver id can not be null")
    private UUID receiverId;

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "1.00", message = "Transfer amount should be greater than 1.00")
    private BigDecimal amount;
}
