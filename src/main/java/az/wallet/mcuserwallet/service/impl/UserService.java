package az.wallet.mcuserwallet.service.impl;

import az.wallet.mcuserwallet.domain.User;
import az.wallet.mcuserwallet.dto.request.UserRegisterRequest;
import az.wallet.mcuserwallet.dto.request.UsernameChangeRequest;
import az.wallet.mcuserwallet.dto.response.UserRegisterResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {
    UserRegisterResponse registerUser(@RequestBody UserRegisterRequest request);
    void changeUsername(@RequestBody UsernameChangeRequest request);
}
