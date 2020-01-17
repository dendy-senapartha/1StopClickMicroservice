package com.microservice.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 *
 */
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    //http://olivergierke.de/2013/11/why-field-injection-is-evil/
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;

    @Value("${token.signingKey}")
	private String TOKEN_SIGNING_KEY;
    @Value("${token.validity}")
	private int TOKEN_VALIDITY;
    @Value("${token.refreshValidity}")
	private int TOKEN_REFRESH_VALIDITY;

    @Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints){
		endpoints.tokenStore(tokenStore()).accessTokenConverter(accessTokenConverter())
				.authenticationManager(authenticationManager);
	}

	@Override
	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				.withClient("internal")
				.secret(passwordEncoder.encode("secret"))
				.autoApprove(true)
				.authorizedGrantTypes("password", "refresh_token")
				.scopes("read", "write").accessTokenValiditySeconds(TOKEN_VALIDITY)
				.refreshTokenValiditySeconds(TOKEN_REFRESH_VALIDITY);
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(TOKEN_SIGNING_KEY);
		// final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("mytest.jks"), "mypass".toCharArray());
		// converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));
		return converter;
	}
}
