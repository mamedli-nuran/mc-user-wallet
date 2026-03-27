package az.wallet.mcuserwallet;

import org.springframework.boot.SpringApplication;

public class TestMcUserWalletApplication {

    public static void main(String[] args) {
        SpringApplication.from(McUserWalletApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
