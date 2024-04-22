package br.com.sysmap.bootcamp.controllers;

import br.com.sysmap.bootcamp.domain.dtos.user.*;
import br.com.sysmap.bootcamp.domain.entities.User;
import br.com.sysmap.bootcamp.domain.entities.UserRole;
import br.com.sysmap.bootcamp.domain.services.UserService;
import br.com.sysmap.bootcamp.domain.services.WalletService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final WalletService walletService;



    @Autowired
    public UserController(UserService userService, WalletService walletService) {
        this.userService = userService;
        this.walletService = walletService;

    }

//GET
    @GetMapping("/{id}")
    ResponseEntity<ResponseOneUserDTO> findUser(@PathVariable String id) {

        var principal = userService.getPrincipal();

        if (principal.getId().equals(id)) {
            var user = userService.findById(id);

            var wallet = walletService.getWallet(user.getEmail());

            return ResponseEntity.ok(new ResponseOneUserDTO(user.getId(), user.getUsername(), user.getEmail(), wallet));
        }

        return ResponseEntity.notFound().build();

    }

    @GetMapping("/")
    ResponseEntity<List<ResponseUsersDTO>> findAll() {

        var users = userService.findAll().stream()
                .map(user ->
                        new ResponseUsersDTO(user.getId(), user.getUsername(), user.getEmail())).toList();

        return ResponseEntity.ok(users);
    }

//POST
    @PostMapping("/create")
    ResponseEntity createUser(@RequestBody @Valid CreateUserDTO userData) throws URISyntaxException {
        var possibleUser = userService.findUserByEmail(userData.email());

        if(possibleUser != null) {
            return ResponseEntity.badRequest().body("Email already registered");
        }

        var newUser = userService.createUser(new User(userData.username(), userData.email(), userData.password(), UserRole.USER));

        var wallet = walletService.getWallet(newUser.getEmail());

        return ResponseEntity
                    .created(new URI("/api/users/" + newUser.getId()))
                    .body(new ResponseOneUserDTO(newUser.getId(), newUser.getUsername(), newUser.getEmail(), wallet));

    }

    @PostMapping("/auth")
    ResponseEntity login(@RequestBody @Valid AuthenticationDTO userData) {

        var token = userService.login(userData);


        return ResponseEntity.ok(token);

    }

//PUT
    @PutMapping("/update/{id}")
    @Transactional
    ResponseEntity<Void> updateUser(@PathVariable String id, @RequestBody @Valid UpdateUserDTO userData) {
        var response = userService.update(id, userData);

        if (response != null) {
            return ResponseEntity.noContent().build();
        }

        throw new EntityNotFoundException();
    }

}
