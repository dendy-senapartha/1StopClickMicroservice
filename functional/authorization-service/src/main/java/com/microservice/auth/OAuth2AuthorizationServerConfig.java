package com.microservice.auth;

import lombok.RequiredArgsConstructor;
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
	private final Environment env;

	private static final String TOKEN_SIGNING_KEY = "token.signingKey";
	private static final String TOKEN_VALIDITY = "token.validity";
	private static final String TOKEN_REFRESH_VALIDITY = "token.refreshValidity";

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
				.scopes("read", "write").accessTokenValiditySeconds(env.getRequiredProperty(TOKEN_VALIDITY, Integer.class))
				.refreshTokenValiditySeconds(env.getRequiredProperty(TOKEN_REFRESH_VALIDITY, Integer.class));
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(env.getRequiredProperty(TOKEN_SIGNING_KEY, String.class));
		// final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("mytest.jks"), "mypass".toCharArray());
		// converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));
		return converter;
	}
}
