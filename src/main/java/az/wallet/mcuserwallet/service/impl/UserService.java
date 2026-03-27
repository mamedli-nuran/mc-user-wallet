package az.wallet.mcuserwallet.service.impl;

import az.wallet.mcuserwallet.dto.request.UserRegisterRequest;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {
    void registerUser(@RequestBody UserRegisterRequest request);
}
