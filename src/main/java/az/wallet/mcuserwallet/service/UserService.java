package az.wallet.mcuserwallet.service;

import az.wallet.mcuserwallet.domain.User;
import az.wallet.mcuserwallet.domain.Wallet;
import az.wallet.mcuserwallet.domain.enums.Currency;
import az.wallet.mcuserwallet.domain.enums.Role;
import az.wallet.mcuserwallet.domain.enums.WalletStatus;
import az.wallet.mcuserwallet.dto.request.UserRegisterRequest;
import az.wallet.mcuserwallet.dto.response.UserRegisterResponse;
import az.wallet.mcuserwallet.exception.RegisterNotCompleteException;
import az.wallet.mcuserwallet.repository.UserRepository;
import az.wallet.mcuserwallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements az.wallet.mcuserwallet.service.impl.UserService {
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;


    public UserRegisterResponse registerUser(UserRegisterRequest request) {

        User user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.USER)
                .build();
        try {
            userRepository.save(user);
        } catch (Exception e){
            throw new RegisterNotCompleteException(e.getMessage());
        }

        walletRepository.save(Wallet.builder()
                        .status(WalletStatus.ACTIVE)
                        .balance(new BigDecimal(0))
                        .currency(Currency.AZN)
                        .user(user)
                .build());

        log.info("User {} has been registered and created wallet successfully", user.getUsername());
        return UserRegisterResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
