package az.wallet.mcuserwallet.service.impl;

import az.wallet.mcuserwallet.dto.request.WalletTopUpRequest;
import az.wallet.mcuserwallet.dto.response.WalletInformationResponse;
import az.wallet.mcuserwallet.dto.response.WalletTopUpResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface WalletService {
    WalletInformationResponse  getWalletInformation(@PathVariable UUID userId);
    WalletTopUpResponse topUpBalance(@PathVariable UUID id,@RequestBody WalletTopUpRequest walletTopUpRequest);
}
