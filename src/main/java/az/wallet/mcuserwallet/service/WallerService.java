package az.wallet.mcuserwallet.service;

import az.wallet.mcuserwallet.domain.User;
import az.wallet.mcuserwallet.domain.Wallet;
import az.wallet.mcuserwallet.dto.response.WalletInformationResponse;
import az.wallet.mcuserwallet.exception.WalletNotFoundException;
import az.wallet.mcuserwallet.repository.UserRepository;
import az.wallet.mcuserwallet.repository.WalletRepository;
import az.wallet.mcuserwallet.service.impl.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class WallerService implements WalletService {
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    @Override
    public WalletInformationResponse getWalletInformation(UUID userId) {
        Optional<User> optionalUser = userRepository.findById(userId);


        if (optionalUser.isEmpty()) {
            throw new WalletNotFoundException("Wallet with id " + userId + " not found");
        }

        return WalletInformationResponse.builder()
                .balance(optionalUser.get().getWallet().getBalance())
                .currency(optionalUser.get().getWallet().getCurrency())
                .status(optionalUser.get().getWallet().getStatus())
                .build();
    }
}
