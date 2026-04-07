package az.wallet.mcuserwallet.client;


import az.wallet.mcuserwallet.dto.request.TransactionSaveRequest;
import az.wallet.mcuserwallet.dto.response.TransactionResponse;
import az.wallet.mcuserwallet.dto.response.TransactionSaveResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "mc-transaction-history", url = "${services.transaction-history.url}")
public interface TransactionHistoryClient {
    @PostMapping("/transaction-history/save")
    TransactionSaveResponse save(@RequestBody TransactionSaveRequest request);

    @GetMapping("/transaction-history/wallet/{walletId}")
    List<TransactionResponse> getTransactionsInWallet(@PathVariable UUID walletId);
}
