package az.wallet.mcuserwallet.service;

import az.wallet.mcuserwallet.client.TransactionHistoryClient;
import az.wallet.mcuserwallet.domain.Wallet;
import az.wallet.mcuserwallet.domain.enums.WalletStatus;
import az.wallet.mcuserwallet.dto.request.TransferRequest;
import az.wallet.mcuserwallet.dto.response.TransferResponse;
import az.wallet.mcuserwallet.exception.InsufficientBalanceException;
import az.wallet.mcuserwallet.exception.WalletNotActiveException;
import az.wallet.mcuserwallet.exception.WalletNotFoundException;
import az.wallet.mcuserwallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.http.converter.autoconfigure.ClientHttpMessageConvertersCustomizer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransferService {
    private final WalletRepository walletRepository;
    private final TransactionHistoryClient transactionHistoryClient;
    private final SaveFailedTransactionService saveFailedTransactionService;

    @Transactional
    public TransferResponse makeTransfer(TransferRequest request) {
        Wallet senderWallet = walletRepository
                .findById(request.getSenderId())
                .orElseThrow(() -> new WalletNotFoundException("Wallet with UUID: " + request.getSenderId() + " not found"));

        Wallet receiverWallet = walletRepository
                .findById(request.getReceiverId())
                .orElseThrow(() -> new WalletNotFoundException("Wallet with UUID: " + request.getReceiverId() + " not found"));

        // TODO handle this exceptions
        if (!senderWallet.getStatus().equals(WalletStatus.ACTIVE)) {
            throw new WalletNotActiveException("Sender wallet with id: " + request.getSenderId() + " not active");
        }

        if (!receiverWallet.getStatus().equals(WalletStatus.ACTIVE))
            throw new WalletNotActiveException("Receiver wallet with id: " + request.getReceiverId() + " not active");

        if (senderWallet.getBalance().compareTo(request.getAmount()) < 0) {
            saveFailedTransactionService.saveFailedTransaction(senderWallet.getId(), request.getAmount());
            throw new InsufficientBalanceException("Insufficient balance");
        }


        senderWallet.withdraw(request.getAmount());
        receiverWallet.deposit(request.getAmount());

        return transactionHistoryClient.saveTransferInHistory(request);
    }
}
