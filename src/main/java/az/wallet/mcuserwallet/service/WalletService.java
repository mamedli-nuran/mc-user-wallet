package az.wallet.mcuserwallet.service;

import az.wallet.mcuserwallet.client.TransactionHistoryClient;
import az.wallet.mcuserwallet.domain.User;
import az.wallet.mcuserwallet.domain.Wallet;
import az.wallet.mcuserwallet.domain.enums.TransactionStatus;
import az.wallet.mcuserwallet.domain.enums.TransactionType;
import az.wallet.mcuserwallet.domain.enums.WalletCurrency;
import az.wallet.mcuserwallet.domain.enums.WalletStatus;
import az.wallet.mcuserwallet.dto.request.WalletTopUpRequest;
import az.wallet.mcuserwallet.dto.response.TransactionResponse;
import az.wallet.mcuserwallet.dto.response.TransactionSaveResponse;
import az.wallet.mcuserwallet.dto.response.WalletInformationResponse;
import az.wallet.mcuserwallet.dto.response.WalletTopUpResponse;
import az.wallet.mcuserwallet.exception.WalletNotFoundException;
import az.wallet.mcuserwallet.mapper.TransactionMapper;
import az.wallet.mcuserwallet.mapper.WalletMapper;
import az.wallet.mcuserwallet.repository.UserRepository;
import az.wallet.mcuserwallet.repository.WalletRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletService implements az.wallet.mcuserwallet.service.impl.WalletService {
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final WalletMapper walletMapper;
    private final TransactionMapper transactionMapper;
    private final SaveFailedTransactionService transactionHistoryService;
    private final TransactionHistoryClient transactionHistoryClient;

    @Override
    public WalletInformationResponse getWalletInformation(UUID userId) {
        Optional<User> optionalUser = userRepository.findById(userId);


        if (optionalUser.isEmpty()) {
            throw new WalletNotFoundException("User's wallet with  user id " + userId + " not found");
        }

        return WalletInformationResponse.builder()
                .balance(optionalUser.get().getWallet().getBalance())
                .walletCurrency(optionalUser.get().getWallet().getWalletCurrency())
                .status(optionalUser.get().getWallet().getStatus())
                .build();
    }

    public void createWalletForUser(User user) {
        Wallet wallet = Wallet.builder()
                .status(WalletStatus.ACTIVE)
                .balance(BigDecimal.ZERO)
                .walletCurrency(WalletCurrency.AZN)
                .user(user)
                .build();

        walletRepository.save(wallet);
    }


    @Transactional
    public WalletTopUpResponse topUpBalance(UUID userId, WalletTopUpRequest request) {
        Optional<Wallet> optionalWallet = walletRepository.findByUserId(userId);

        if (optionalWallet.isEmpty()) {
            transactionHistoryService.saveFailedByUUIDTransaction(request);
            throw new WalletNotFoundException("User's wallet with  user id " + userId + " not found");
        }

        if (!optionalWallet.get().getStatus().equals(WalletStatus.ACTIVE)) {
            transactionHistoryService.saveFailedByStatusTransaction(request, optionalWallet.get());
            throw new WalletNotFoundException("User's wallet with  user id " + userId + " not active");
        }

        optionalWallet.get().setBalance(optionalWallet.get().getBalance().add(request.getAmount()));

        TransactionSaveResponse response = transactionHistoryClient.save(transactionMapper.toTransactionSaveRequest(
                optionalWallet.get().getId(), request.getAmount(), "Million top up", TransactionType.TOP_UP, TransactionStatus.SUCCESS));

        return walletMapper.toWalletTopUpResponse(optionalWallet.get(), response, "Million top up");
    }

    @Override
    public List<TransactionResponse> getWalletHistory(UUID walletId) {
        if (!walletRepository.existsById(walletId)) {
            throw new WalletNotFoundException("User's wallet with  wallet id " + walletId + " not found");
        }

        return transactionHistoryClient.getTransactionsInWallet(walletId);
    }


}
