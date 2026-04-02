package az.wallet.mcuserwallet.exception;

public class WalletNotActiveException extends RuntimeException {
    public WalletNotActiveException(String message) {
        super(message);
    }
}
