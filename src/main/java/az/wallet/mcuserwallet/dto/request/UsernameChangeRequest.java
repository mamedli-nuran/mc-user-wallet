package az.wallet.mcuserwallet.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class UsernameChangeRequest {
    @NotNull
    private UUID id;

    @NotBlank(message = "Username must not be empty")
    private String username;
}
