package az.wallet.mcuserwallet.service;

import az.wallet.mcuserwallet.client.TransactionHistoryClient;
import az.wallet.mcuserwallet.domain.Wallet;
import az.wallet.mcuserwallet.domain.enums.TransactionStatus;
import az.wallet.mcuserwallet.domain.enums.TransactionType;
import az.wallet.mcuserwallet.dto.request.WalletTopUpRequest;
import az.wallet.mcuserwallet.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TransactionHistoryService {
    private final TransactionMapper transactionMapper;
    private final TransactionHistoryClient transactionHistoryClient;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveFailedByUUIDTransaction(WalletTopUpRequest request) {
        transactionHistoryClient.save(transactionMapper.toTransactionSaveRequest(
                null, request.getAmount(), "Million top up", TransactionType.TOPUP, TransactionStatus.FAILED
        ));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveFailedByStatusTransaction(WalletTopUpRequest request, Wallet wallet) {
        transactionHistoryClient.save(transactionMapper.toTransactionSaveRequest(
                wallet.getId(), request.getAmount(), "Million top up", TransactionType.TOPUP, TransactionStatus.FAILED
        ));
    }
}
