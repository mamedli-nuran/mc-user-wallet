package az.wallet.mcuserwallet.service;

import az.wallet.mcuserwallet.domain.User;
import az.wallet.mcuserwallet.domain.Wallet;
import az.wallet.mcuserwallet.domain.enums.WalletCurrency;
import az.wallet.mcuserwallet.domain.enums.WalletStatus;
import az.wallet.mcuserwallet.dto.response.WalletInformationResponse;
import az.wallet.mcuserwallet.exception.WalletNotFoundException;
import az.wallet.mcuserwallet.repository.UserRepository;
import az.wallet.mcuserwallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletService implements az.wallet.mcuserwallet.service.impl.WalletService {
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

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

    public void createWalletForUser(User user){
        Wallet wallet = Wallet.builder()
                .status(WalletStatus.ACTIVE)
                .balance(BigDecimal.ZERO)
                .walletCurrency(WalletCurrency.AZN)
                .user(user)
                .build();

        walletRepository.save(wallet);
    }
}
