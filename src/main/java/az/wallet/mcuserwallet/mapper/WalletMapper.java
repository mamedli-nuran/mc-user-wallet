package az.wallet.mcuserwallet.mapper;

import az.wallet.mcuserwallet.domain.Wallet;
import az.wallet.mcuserwallet.dto.response.TransactionSaveResponse;
import az.wallet.mcuserwallet.dto.response.WalletTopUpResponse;
import org.springframework.stereotype.Component;

@Component
public class WalletMapper {

    public WalletTopUpResponse toWalletTopUpResponse(Wallet wallet, TransactionSaveResponse response, String provider) {
        return WalletTopUpResponse.builder()
                .userId(wallet.getUser().getId())
                .walletId(wallet.getId())
                .transactionId(response.getTransactionId())
                .createdAt(response.getCreatedAt())
                .balance(wallet.getBalance())
                .currency(wallet.getWalletCurrency())
                .provider(provider)
                .build();
    }
}
