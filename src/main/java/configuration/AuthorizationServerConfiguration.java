package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import security.CustomAuthentication;

import java.time.Duration;
import java.util.Collection;
import java.util.List;

@Configuration
@EnableWebSecurity
public class AuthorizationServerConfiguration {

    @Bean
    @Order(1)
    public SecurityFilterChain authServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());

        http.oauth2ResourceServer(oauth2Rs -> oauth2Rs.jwt(Customizer.withDefaults()));

        http.formLogin(configurer -> configurer.loginPage("/login"));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public TokenSettings tokenSettings() {
        return TokenSettings.builder()
                .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
                .accessTokenTimeToLive(Duration.ofMinutes(60))
                .refreshTokenTimeToLive(Duration.ofMinutes(90))
                .build();
    }

    @Bean
    public ClientSettings clientSettings() {
        return ClientSettings.builder()
                .requireAuthorizationConsent(false)
                .build();
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
                .tokenEndpoint("/oauth2/token")
                .tokenIntrospectionEndpoint("/oauth2/introspect")
                .tokenRevocationEndpoint("/oauth2/revoke")
                .authorizationEndpoint("/oauth2/authorize")
                .oidcUserInfoEndpoint("/oauth2/userinfo")
                .jwkSetEndpoint("/oauth2/jwks")
                .oidcUserInfoEndpoint("/oauth2/logout")
                .build();
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer() {
        return context -> {

            if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {

                var authentication = context.getPrincipal();

                List<String> authorities = authentication.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList();

                context.getClaims()
                        .claim("authorities", authorities)
                        .claim("username", authentication.getName());
            }
        };
    }
}
