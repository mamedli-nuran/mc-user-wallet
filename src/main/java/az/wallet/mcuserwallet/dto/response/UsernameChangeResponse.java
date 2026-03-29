package az.wallet.mcuserwallet.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class UsernameChangeResponse {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private UUID userId;
    private String username;
    private String path;
}
