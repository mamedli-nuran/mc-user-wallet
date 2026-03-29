package az.wallet.mcuserwallet.service;

import az.wallet.mcuserwallet.domain.User;
import az.wallet.mcuserwallet.domain.Wallet;
import az.wallet.mcuserwallet.domain.enums.Currency;
import az.wallet.mcuserwallet.domain.enums.Role;
import az.wallet.mcuserwallet.domain.enums.WalletStatus;
import az.wallet.mcuserwallet.dto.request.UserRegisterRequest;
import az.wallet.mcuserwallet.dto.request.UsernameChangeRequest;
import az.wallet.mcuserwallet.dto.response.UserRegisterResponse;
import az.wallet.mcuserwallet.exception.EmailAlreadyExistsException;
import az.wallet.mcuserwallet.exception.RegisterNotCompleteException;
import az.wallet.mcuserwallet.exception.UserNotFoundException;
import az.wallet.mcuserwallet.repository.UserRepository;
import az.wallet.mcuserwallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements az.wallet.mcuserwallet.service.impl.UserService {
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    @Transactional
    public UserRegisterResponse registerUser(UserRegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.USER)
                .build();

        userRepository.save(user);

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

    @Transactional
    public void changeUsername(UsernameChangeRequest request) {
        User user = userRepository.findById(request.getId())
                        .orElseThrow(() -> new UserNotFoundException("User with id: " + request.getId() + " not found"));

        user.setUsername(request.getUsername());
    }
}
