package security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import service.UserService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtCustomAuthenticadedFilter extends OncePerRequestFilter {

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String login = authentication.getName();
        User user = userService.getForLogin(login);

        if (user != null) {
            authentication = new CustomAuthentication(user);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }



        filterChain.doFilter(request, response);
    }
    private boolean needToConvert(Authentication authentication) {
        return authentication != null && authentication instanceof JwtAuthenticationToken;
    }
}
