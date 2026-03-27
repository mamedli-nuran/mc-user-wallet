package az.wallet.mcuserwallet.dto.request;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String name;
    private String surname;
    private String username;
    private String email;
}
