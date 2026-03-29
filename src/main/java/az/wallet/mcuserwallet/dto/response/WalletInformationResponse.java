package az.wallet.mcuserwallet.dto.response;

import az.wallet.mcuserwallet.domain.enums.WalletCurrency;
import az.wallet.mcuserwallet.domain.enums.WalletStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class WalletInformationResponse {
    private BigDecimal balance;
    private WalletCurrency walletCurrency;
    private WalletStatus status;
}
