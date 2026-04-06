package az.wallet.mcuserwallet.mapper;

import az.wallet.mcuserwallet.domain.enums.TransactionStatus;
import az.wallet.mcuserwallet.domain.enums.TransactionType;
import az.wallet.mcuserwallet.dto.request.TransactionSaveRequest;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.UUID;

@Component
public class TransactionMapper {

    public TransactionSaveRequest toTransactionSaveRequest(UUID walletId, BigDecimal amount, String provider, TransactionType transactionType, TransactionStatus transactionStatus) {
        return TransactionSaveRequest.builder()
                .walletId(walletId)
                .amount(amount)
                .provider(provider)
                .type(transactionType)
                .status(transactionStatus)
                .build();
    }
}
