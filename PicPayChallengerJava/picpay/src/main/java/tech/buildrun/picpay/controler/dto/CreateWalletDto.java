package tech.buildrun.picpay.controler.dto;

import tech.buildrun.picpay.entity.Wallet;
import tech.buildrun.picpay.entity.WalletType;


public record CreateWalletDto(String cpfCnpj,
                              String email,
                              String fullName,
                              String password,
                              WalletType.EnumWalletType walletType) {

    public Wallet toWallet() {
        return new Wallet(
                cpfCnpj,
                email,
                fullName,
                password,
                walletType.get()
        );
    }

}
