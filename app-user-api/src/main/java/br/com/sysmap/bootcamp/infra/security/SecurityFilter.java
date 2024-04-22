package br.com.sysmap.bootcamp.infra.security;

import br.com.sysmap.bootcamp.domain.repository.UserRepository;
import br.com.sysmap.bootcamp.domain.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {


    private final TokenService tokenService;

    private final UserRepository userRepository;

    @Autowired
    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = recoverToken(request);


        if (token != null) {

            var email = tokenService.validateToken(token);

            UserDetails user = userRepository.findByEmail(email);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }


        filterChain.doFilter(request, response);
    }


    private String recoverToken(HttpServletRequest request) {
        //get the header
        var authHeader = request.getHeader("Authorization");

        //check if header is null
        if(authHeader == null) return null;

        return authHeader.replace("Bearer ", "");

    }
}

