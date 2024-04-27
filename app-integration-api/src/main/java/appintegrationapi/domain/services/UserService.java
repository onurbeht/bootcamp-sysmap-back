package appintegrationapi.domain.services;

import appintegrationapi.domain.entities.User;
import appintegrationapi.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUser() {
        var user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return findByEmail(user.getEmail());
    }
}
