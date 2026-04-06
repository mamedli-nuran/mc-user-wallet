package az.wallet.mcuserwallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class McUserWalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(McUserWalletApplication.class, args);
    }

}
