package dev.thejavaman.springboot.oauth2authorizationserver.jwt.config;

import java.security.KeyPair;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
               .withClient("clientId")
               .secret(passwordEncoder.encode("clientSecret"))
               .scopes("phone", "email")
               .authorizedGrantTypes("password", "authorization_code", "refresh_token")
               .autoApprove(true)
               .redirectUris("https://thejavaman.dev/")
               .and()
               .withClient("secondClientId")
               .secret(passwordEncoder.encode("secondClientSecret"))
               .scopes("email")
               .authorizedGrantTypes("authorization_code", "refresh_token")
               .autoApprove(true)
               .redirectUris("https://thejavaman.dev/")
               .accessTokenValiditySeconds(3600)
               .refreshTokenValiditySeconds(2592000);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                 .accessTokenConverter(accessTokenConverter())
                 .userDetailsService(userDetailsService)
                 .authenticationManager(authenticationManager);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        var converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair());
        return converter;

    }

    private KeyPair keyPair() {
        return new KeyStoreKeyFactory(new ClassPathResource("/jwt/jwt.jks"),
                                      "pass@123".toCharArray()).getKeyPair("jwt");

    }
}
