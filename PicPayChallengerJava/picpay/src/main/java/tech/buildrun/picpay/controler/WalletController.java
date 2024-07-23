package tech.buildrun.picpay.controler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.buildrun.picpay.controler.dto.CreateWalletDto;
import tech.buildrun.picpay.service.WalletService;
import tech.buildrun.picpay.entity.Wallet;

@RestController
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody CreateWalletDto dto) {
        var wallet = walletService.createWallet(dto);

        return ResponseEntity.ok(wallet);
    }

}
