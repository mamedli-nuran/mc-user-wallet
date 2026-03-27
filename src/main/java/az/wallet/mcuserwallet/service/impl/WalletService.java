package az.wallet.mcuserwallet.service.impl;

import az.wallet.mcuserwallet.dto.response.WalletInformationResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public interface WalletService {
    WalletInformationResponse  getWalletInformation(@PathVariable UUID userId);
}
