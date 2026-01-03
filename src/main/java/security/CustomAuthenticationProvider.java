package security;

import lombok.RequiredArgsConstructor;
import models.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import service.UserService;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String passwordDigited = authentication.getCredentials().toString();

        User userFound = userService.getForLogin(login);

        if (userFound == null) {
            throw getUserErrorNotFound();
        }

        String passwordCriptografed = userFound.getPassword();

        boolean passwordCheck = passwordEncoder.matches(passwordDigited, passwordCriptografed);

        if (passwordCheck) {
            return new CustomAuthentication(userFound);
        }
        throw getUserErrorNotFound();
    }

    private UsernameNotFoundException getUserErrorNotFound() {
        return new UsernameNotFoundException("Username or password not right");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}

