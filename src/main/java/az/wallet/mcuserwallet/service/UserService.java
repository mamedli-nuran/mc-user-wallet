package az.wallet.mcuserwallet.service;

import az.wallet.mcuserwallet.domain.User;
import az.wallet.mcuserwallet.dto.request.UserRegisterRequest;
import az.wallet.mcuserwallet.dto.request.UsernameChangeRequest;
import az.wallet.mcuserwallet.dto.response.UserInfoResponse;
import az.wallet.mcuserwallet.dto.response.UserRegisterResponse;
import az.wallet.mcuserwallet.dto.response.UsernameChangeResponse;
import az.wallet.mcuserwallet.exception.EmailAlreadyExistsException;
import az.wallet.mcuserwallet.exception.UserNotFoundException;
import az.wallet.mcuserwallet.mapper.UserMapper;
import az.wallet.mcuserwallet.repository.UserRepository;
import az.wallet.mcuserwallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements az.wallet.mcuserwallet.service.impl.UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final WalletService walletService;



    @Transactional
    public UserRegisterResponse registerUser(UserRegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = userMapper.createUser(request);
        userRepository.save(user);
        walletService.createWalletForUser(user);

        log.info("User {} has been registered and created wallet successfully", user.getUsername());
        return userMapper.createUserRegisterResponse(user);
    }

    @Transactional
    public UsernameChangeResponse changeUsername(UsernameChangeRequest requestBody) {
        User user = userRepository.findById(requestBody.getId())
                        .orElseThrow(() -> new UserNotFoundException("User with id: " + requestBody.getId() + " not found"));
        user.setUsername(requestBody.getUsername());

        return userMapper.changeUsernameResponse(requestBody);
    }

    @Override
    public UserInfoResponse getUserInfoById(UUID userID) {
        Optional<User> optionalUser = userRepository.findById(userID);
        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with id: " + userID + " not found");
        }

        return userMapper.userToUserInfoResponse(optionalUser.get());
    }
}
