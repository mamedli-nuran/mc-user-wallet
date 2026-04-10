package az.wallet.mcuserwallet.service;

import az.wallet.mcuserwallet.client.TransactionHistoryClient;
import az.wallet.mcuserwallet.domain.Wallet;
import az.wallet.mcuserwallet.dto.request.TransferRequest;
import az.wallet.mcuserwallet.dto.response.TransferResponse;
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
    private final TransactionHistoryClient  transactionHistoryClient;
    private final ClientHttpMessageConvertersCustomizer clientConvertersCustomizer;

    @Transactional
    public TransferResponse makeTransfer(TransferRequest request) {
        // TODO сделать проверку на активность кошелька и достаточную сумму денег на нем
        Wallet senderWallet = walletRepository
                .findById(request.getSenderId())
                .orElseThrow(() -> new WalletNotFoundException("Wallet with UUID: " + request.getSenderId() + " not found"));

        Wallet receiverWallet = walletRepository
                .findById(request.getReceiverId())
                .orElseThrow(() -> new WalletNotFoundException("Wallet with UUID: " + request.getReceiverId() + " not found"));


        senderWallet.withdraw(request.getAmount());
        receiverWallet.deposit(request.getAmount());

        return transactionHistoryClient.saveTransferInHistory(request);
    }
}
