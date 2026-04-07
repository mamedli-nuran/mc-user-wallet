package az.wallet.mcuserwallet.dto.response;

import az.wallet.mcuserwallet.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// name surname email role
public class UserInfoResponse {
    private String name;
    private String surname;
    private String email;
    private UserRole role;
}
