package br.com.sysmap.bootcamp.domain.services;

import br.com.sysmap.bootcamp.domain.dtos.user.AuthenticationDTO;
import br.com.sysmap.bootcamp.domain.dtos.user.UpdateUserDTO;
import br.com.sysmap.bootcamp.domain.entities.User;
import br.com.sysmap.bootcamp.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private final UserRepository repository;
    private final WalletService walletService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;


    @Autowired
    UserService (UserRepository repository, WalletService walletService,TokenService tokenService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.walletService = walletService;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    public User findUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User createUser(User user) {

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());

        User newUser = new User(user.getUsername(), user.getEmail(), encryptedPassword, user.getRole());

        var userCreated = repository.save(newUser);

        walletService.createWallet(userCreated.getEmail());

        return userCreated;
    }

    public String login(AuthenticationDTO userData) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(userData.email(), userData.password());

        var auth = authenticationManager.authenticate(usernamePassword);

        return tokenService.generateToken((User) auth.getPrincipal());

    }

    public User findById(String id) {

        var possibleUser = repository.findById(id);

        if(possibleUser.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return possibleUser.get();
    }


    public List<User> findAll() {

        var users = repository.findAll();

        if (users.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return users;
    }

    public User update(String id, UpdateUserDTO userData) {
        var oldUser = repository.findById(id);

        if(oldUser.isPresent()) {
            User updatedUser = oldUser.get();

            updatedUser.setUsername(userData.username());

            String encryptedPassword = new BCryptPasswordEncoder().encode(userData.password());

            updatedUser.setPassword(encryptedPassword);

            return updatedUser;
        }

        return null;
    }

    public User getPrincipal() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
