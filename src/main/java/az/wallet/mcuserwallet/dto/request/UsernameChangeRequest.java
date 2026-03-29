package az.wallet.mcuserwallet.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class UsernameChangeRequest {
    private UUID id;
    private String username;
}
