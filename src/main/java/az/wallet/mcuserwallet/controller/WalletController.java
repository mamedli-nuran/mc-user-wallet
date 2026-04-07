package az.wallet.mcuserwallet.controller;

import az.wallet.mcuserwallet.domain.Wallet;
import az.wallet.mcuserwallet.dto.request.WalletTopUpRequest;
import az.wallet.mcuserwallet.dto.response.TransactionResponse;
import az.wallet.mcuserwallet.dto.response.WalletInformationResponse;
import az.wallet.mcuserwallet.dto.response.WalletTopUpResponse;
import az.wallet.mcuserwallet.repository.WalletRepository;
import az.wallet.mcuserwallet.service.impl.WalletService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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


    @PostMapping("/{userId}/topup")
    public ResponseEntity<WalletTopUpResponse> topUpWallet(
            @PathVariable UUID userId, @Valid @RequestBody WalletTopUpRequest request) {

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(walletService.topUpBalance(userId, request));

    }

    @GetMapping("/history/{walletId}")
    public ResponseEntity<List<TransactionResponse>> getWalletHistory(@PathVariable @NotNull UUID walletId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(walletService.getWalletHistory(walletId));
    }

}
