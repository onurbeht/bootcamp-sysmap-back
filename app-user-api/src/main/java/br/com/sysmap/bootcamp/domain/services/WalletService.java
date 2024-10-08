package br.com.sysmap.bootcamp.domain.services;

import br.com.sysmap.bootcamp.domain.dtos.wallet.WalletDTO;
import br.com.sysmap.bootcamp.domain.entities.Wallet;
import br.com.sysmap.bootcamp.domain.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WalletService {

    private final WalletRepository repository;

    @Autowired
    public WalletService(WalletRepository repository) {
        this.repository = repository;
    }

    public Wallet getWallet(String owner) {

        return repository.findByOwner(owner);
    }

    public Wallet createWallet(String owner) {
        var wallet = new Wallet();
        wallet.setBalance(0.0);
        wallet.setPoints(0);
        wallet.setLastUpdate(LocalDate.now());
        wallet.setOwner(owner);

        return repository.save(wallet);

    }

    public Wallet sumValue(String owner, Double value) {

        Wallet updatedWallet = getWallet(owner);

        updatedWallet.setBalance(updatedWallet.getBalance() + value);
        updatedWallet.setLastUpdate(LocalDate.now());

        return updatedWallet;
    }

    @Transactional
    public void debit(WalletDTO walletData) {

        Wallet wallet = getWallet(walletData.getEmail());

        wallet.setBalance(wallet.getBalance() - walletData.getValue());
        wallet.setLastUpdate(LocalDate.now());

    }

}
