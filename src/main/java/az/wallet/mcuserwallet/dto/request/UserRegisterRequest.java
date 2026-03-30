package az.wallet.mcuserwallet.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterRequest {

    @NotBlank(message = "Name must not be empty")
    private String name;

    @NotBlank(message = "Surname must not be empty")
    private String surname;

    @NotBlank(message = "Username must not be empty")
    private String username;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email must not be empty")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
