package az.wallet.mcuserwallet.mapper;

import az.wallet.mcuserwallet.domain.User;
import az.wallet.mcuserwallet.domain.enums.UserRole;
import az.wallet.mcuserwallet.dto.request.UserRegisterRequest;
import az.wallet.mcuserwallet.dto.request.UsernameChangeRequest;
import az.wallet.mcuserwallet.dto.response.UserRegisterResponse;
import az.wallet.mcuserwallet.dto.response.UsernameChangeResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    public User createUser(UserRegisterRequest requestBody){
        return User.builder()
                .name(requestBody.getName())
                .surname(requestBody.getSurname())
                .username(requestBody.getUsername())
                .email(requestBody.getEmail())
                .password(requestBody.getPassword())
                .userRole(UserRole.USER)
                .build();
    }

    public UsernameChangeResponse changeUsernameResponse(UsernameChangeRequest body) {
        return UsernameChangeResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(202)
                .message("Username changed successfully")
                .userId(body.getId())
                .username(body.getUsername())
                .build();
    }

    public UserRegisterResponse createUserRegisterResponse(User user) {
        return UserRegisterResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
