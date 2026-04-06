package az.wallet.mcuserwallet.client;


import az.wallet.mcuserwallet.dto.request.TransactionSaveRequest;
import az.wallet.mcuserwallet.dto.response.TransactionSaveResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mc-transaction-history", url = "${services.transaction-history.url}")
public interface TransactionHistoryClient {
    @PostMapping("/transaction-history/save")
    TransactionSaveResponse save(@RequestBody TransactionSaveRequest request);
}
