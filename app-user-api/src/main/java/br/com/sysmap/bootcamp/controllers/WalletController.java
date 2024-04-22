package br.com.sysmap.bootcamp.controllers;

import br.com.sysmap.bootcamp.domain.dtos.wallet.RequestDTO;
import br.com.sysmap.bootcamp.domain.entities.User;
import br.com.sysmap.bootcamp.domain.entities.Wallet;
import br.com.sysmap.bootcamp.domain.services.UserService;
import br.com.sysmap.bootcamp.domain.services.WalletService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;
    private final UserService userService;

    @Autowired
    public WalletController(WalletService walletService, UserService userService) {
        this.walletService = walletService;
        this.userService = userService;
    }

//GET


    @GetMapping
    public ResponseEntity<Wallet> getWallet() {

        User user = userService.getPrincipal();

        Wallet wallet = walletService.getWallet(user.getEmail());

        return ResponseEntity.ok(wallet);

    }

//POST
    @Transactional
    @PostMapping("/credit")
    public ResponseEntity<Wallet> updateWallet(@RequestBody @Valid RequestDTO request) {

        User user = userService.getPrincipal();

        var updatedWallet = walletService.sumValue( user.getEmail(), request.value());

        return ResponseEntity.ok(updatedWallet);
    }

}
