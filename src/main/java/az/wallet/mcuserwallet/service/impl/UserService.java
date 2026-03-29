package az.wallet.mcuserwallet.service.impl;

import az.wallet.mcuserwallet.domain.User;
import az.wallet.mcuserwallet.dto.request.UserRegisterRequest;
import az.wallet.mcuserwallet.dto.request.UsernameChangeRequest;
import az.wallet.mcuserwallet.dto.response.UserRegisterResponse;
import az.wallet.mcuserwallet.dto.response.UsernameChangeResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {
    UserRegisterResponse registerUser(@RequestBody UserRegisterRequest request);
    UsernameChangeResponse changeUsername(@RequestBody UsernameChangeRequest request);
}
