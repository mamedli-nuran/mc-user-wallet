package az.wallet.mcuserwallet.controller;

import az.wallet.mcuserwallet.dto.request.UserRegisterRequest;
import az.wallet.mcuserwallet.dto.request.UsernameChangeRequest;
import az.wallet.mcuserwallet.dto.response.UserRegisterResponse;
import az.wallet.mcuserwallet.dto.response.UsernameChangeResponse;
import az.wallet.mcuserwallet.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> registerUser(@Valid @RequestBody UserRegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.registerUser(request));
    }

    @PostMapping("/change-username")
    public ResponseEntity<UsernameChangeResponse> changeUsername(
            @Valid @RequestBody UsernameChangeRequest requestBody, HttpServletRequest request) {
        UsernameChangeResponse response = userService.changeUsername(requestBody);
        response.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getInfoAboutUser(@PathVariable UUID userId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getUserInfoById(userId));
    }
}
