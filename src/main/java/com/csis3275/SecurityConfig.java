package com.csis3275;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.csis3275.model.UserService;

@Configuration
public class SecurityConfig {

	private UserService customUserDetailsService;

	public SecurityConfig(UserService customUserDetailsService) {
		this.customUserDetailsService = customUserDetailsService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain getSecurityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector)
			throws Exception {
		MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);

		http.csrf(csrfConfigurer -> {
			csrfConfigurer.ignoringRequestMatchers(mvcMatcherBuilder.pattern("/**"), PathRequest.toH2Console());
		});

		http.headers(headersConfigurer -> {
			headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin);
		});

		http.authorizeHttpRequests(auth -> {
			auth.requestMatchers(mvcMatcherBuilder.pattern("/css/**"), mvcMatcherBuilder.pattern("/img/**"),
					mvcMatcherBuilder.pattern("/js/**"), mvcMatcherBuilder.pattern("/webfonts/**")).permitAll();
			auth.requestMatchers(PathRequest.toH2Console()).permitAll();
			auth.requestMatchers(mvcMatcherBuilder.pattern("/login"), mvcMatcherBuilder.pattern("/register"))
					.permitAll();
			auth.requestMatchers(mvcMatcherBuilder.pattern("/freelancer/**")).hasRole("FREELANCER");
			auth.requestMatchers(mvcMatcherBuilder.pattern("/employer/**")).hasRole("EMPLOYER");
			auth.anyRequest().authenticated();
		});

		http.formLogin(l -> {
			l.loginPage("/login").permitAll();
			l.failureHandler((request, response, exception) -> {
				response.sendRedirect("/login?error=Email or password is wrong");
			});
			l.successHandler((request, response, exception) -> {
				response.sendRedirect("/");
			});
		});

//		http.exceptionHandling(e -> {
//			e.accessDeniedHandler((request, response, exception) -> {
//				response.sendRedirect("/404");
//			});
//		});

		http.logout((logout) -> {
			logout.logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();
		});

		return http.build();
	}

	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(authenticationProvider());
		return authenticationManagerBuilder.build();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(customUserDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

}
