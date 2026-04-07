package az.wallet.mcuserwallet.service.impl;

import az.wallet.mcuserwallet.domain.User;
import az.wallet.mcuserwallet.dto.request.UserRegisterRequest;
import az.wallet.mcuserwallet.dto.request.UsernameChangeRequest;
import az.wallet.mcuserwallet.dto.response.UserInfoResponse;
import az.wallet.mcuserwallet.dto.response.UserRegisterResponse;
import az.wallet.mcuserwallet.dto.response.UsernameChangeResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface UserService {
    UserRegisterResponse registerUser(@RequestBody UserRegisterRequest request);
    UsernameChangeResponse changeUsername(@RequestBody UsernameChangeRequest request);
    UserInfoResponse getUserInfoById(@PathVariable UUID userID);
}
