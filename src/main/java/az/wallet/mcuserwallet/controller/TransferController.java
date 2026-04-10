package az.wallet.mcuserwallet.controller;

import az.wallet.mcuserwallet.dto.request.TransferRequest;
import az.wallet.mcuserwallet.dto.response.TransferResponse;
import az.wallet.mcuserwallet.service.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TransferController {
    private final TransferService transferService;

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> createTransfer(@RequestBody @Valid TransferRequest transferRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(transferService.makeTransfer(transferRequest));
    }
}
