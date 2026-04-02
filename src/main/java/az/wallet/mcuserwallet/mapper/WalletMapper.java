package az.wallet.mcuserwallet.mapper;

import az.wallet.mcuserwallet.domain.Wallet;
import az.wallet.mcuserwallet.dto.response.WalletTopUpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class WalletMapper {

    public WalletTopUpResponse toWalletTopUpResponse(Wallet wallet) {
        return WalletTopUpResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.ACCEPTED)
                .message("Balance topped up successfully!")
                .userId(wallet.getId())
                .balance(wallet.getBalance())
                .currency(wallet.getWalletCurrency())
                .build();
    }
}
