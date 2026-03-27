package az.wallet.mcuserwallet.controller;

import az.wallet.mcuserwallet.dto.response.WalletInformationResponse;
import az.wallet.mcuserwallet.repository.WalletRepository;
import az.wallet.mcuserwallet.service.impl.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;


    @GetMapping("/{userId}")
    public ResponseEntity<WalletInformationResponse> getWallet(@PathVariable UUID userId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(walletService.getWalletInformation(userId));
    }
}
