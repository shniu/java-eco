package io.github.shniu.identity.simple;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shniu
 * @date 2019/07/19 18:06:43
 */
public class AuthenticationExample {
    private static AuthenticationManager am = new SimpleAuthenticationManager();

    public void login(final String username, final String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = am.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
    }

    public static class SimpleAuthenticationManager implements AuthenticationManager {
        static final List<GrantedAuthority> AUTHORITIES = new ArrayList<>();

        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            if (authentication.getName().equals(authentication.getCredentials())) {
                return new UsernamePasswordAuthenticationToken(
                        authentication.getName(), authentication.getCredentials(), AUTHORITIES);
            }
            throw new BadCredentialsException("Bad Credentials");
        }
    }
}
