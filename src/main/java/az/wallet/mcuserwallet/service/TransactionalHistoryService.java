package az.wallet.mcuserwallet.service;

import az.wallet.mcuserwallet.domain.Wallet;
import az.wallet.mcuserwallet.domain.enums.TransactionStatus;
import az.wallet.mcuserwallet.domain.enums.TransactionType;
import az.wallet.mcuserwallet.dto.request.WalletTopUpRequest;
import az.wallet.mcuserwallet.mapper.TransactionMapper;
import az.wallet.mcuserwallet.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TransactionalHistoryService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveFailedByUUIDTransaction(WalletTopUpRequest request) {
        transactionRepository.save(
                transactionMapper.toTransaction(null, request, TransactionType.TOPUP, TransactionStatus.FAILED));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveFailedByStatusTransaction(WalletTopUpRequest request, Wallet wallet) {
        transactionRepository.save(
                transactionMapper.toTransaction(wallet.getId(), request, TransactionType.TOPUP, TransactionStatus.FAILED));
    }
}
