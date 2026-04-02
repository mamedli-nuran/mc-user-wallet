package az.wallet.mcuserwallet.mapper;

import az.wallet.mcuserwallet.domain.Transaction;
import az.wallet.mcuserwallet.domain.Wallet;
import az.wallet.mcuserwallet.domain.enums.TransactionStatus;
import az.wallet.mcuserwallet.domain.enums.TransactionType;
import az.wallet.mcuserwallet.dto.request.WalletTopUpRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TransactionMapper {

    public Transaction toTransaction(
            UUID walletId, WalletTopUpRequest request,
            TransactionType transactionType, TransactionStatus transactionStatus) {
        return Transaction.builder()
                .walletId(walletId)
                .type(transactionType)
                .amount(request.getAmount())
                .createdAt(LocalDateTime.now())
                .status(transactionStatus)
                .build();
    }
}
