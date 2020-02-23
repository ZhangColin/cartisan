package com.cartisan.security;

import com.cartisan.constants.CodeMessage;
import com.cartisan.exceptions.CartisanException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * @author colin
 */
@Service
public class LoginService {
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final HashOperations<String, String, Object> hashOperations;

    public static final CodeMessage LOGIN_ERROR = CodeMessage.FAIL.fillArgs("用户名或密码不正确");

    public LoginService(UserDetailsService userDetailsService, JwtTokenProvider tokenProvider,
                        PasswordEncoder passwordEncoder, HashOperations<String, String, Object> hashOperations) {
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.hashOperations = hashOperations;
    }

    public String login(String username, String password){
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (userDetails==null ||
                !passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new CartisanException(LOGIN_ERROR);
        }

        securityLogin(userDetails);

        final String token = tokenProvider.generateToken(userDetails.getUsername());

        hashOperations.put("onlineUsers", userDetails.getUsername(), System.currentTimeMillis());

        return token;
    }

    public void logout(String token) {
        final String userName = tokenProvider.getUserName(token);
        hashOperations.delete("onlineUsers", userName);
    }

    private void securityLogin(UserDetails userDetails) {

        final UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
