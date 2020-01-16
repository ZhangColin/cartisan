package com.cartisan.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT 登录授权过滤器
 * @author colin
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider tokenProvider;
    private final SecurityProperties securityProperties;

    public JwtAuthenticationTokenFilter(UserDetailsService userDetailsService, JwtTokenProvider tokenProvider, SecurityProperties securityProperties) {
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
        this.securityProperties = securityProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(securityProperties.getTokenHeader());
        if (Strings.isNotBlank(authHeader)
                && authHeader.startsWith(securityProperties.getTokenHead())) {
            final String token = authHeader.substring(securityProperties.getTokenHead().length()).trim();
            if (tokenProvider.verify(token) && SecurityContextHolder.getContext().getAuthentication() == null) {
                final String userName = tokenProvider.getUserName(token);
                log.info("checking username:[{}]", userName);

                final UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);

                if (userName.equals(userDetails.getUsername())) {
                    final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    log.info("authenticated user:[{}]", userName);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
