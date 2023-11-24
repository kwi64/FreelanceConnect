package com.csis3275;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import com.csis3275.model.AppUserDetailsService;

@Configuration
public class SecurityConfig {

	private AppUserDetailsService appUserDetailsService;
	public SecurityConfig(AppUserDetailsService appUserDetailsService) {
        this.appUserDetailsService = appUserDetailsService;
    }

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain getSecurityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector)
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
			auth.requestMatchers(mvcMatcherBuilder.pattern("/login"), mvcMatcherBuilder.pattern("/register"), mvcMatcherBuilder.pattern("/error"))
					.permitAll();
			auth.requestMatchers(mvcMatcherBuilder.pattern("/freelancer/**")).hasRole("FREELANCER");
			auth.requestMatchers(mvcMatcherBuilder.pattern("/employer/**")).hasRole("EMPLOYER");
			auth.anyRequest().authenticated();
		});
		

		http.formLogin(l -> {
			l.loginPage("/login").permitAll();
			l.failureHandler((request, response, exception) -> {
				
				final FlashMap flashMap = new FlashMap();
		        final FlashMapManager flashMapManager = new SessionFlashMapManager();

				if (exception instanceof DisabledException) {
			        flashMap.put("error", "Sorry, your account is disabled!");
					response.sendRedirect("/login");
				} else {
					flashMap.put("error", "Email or password is wrong!");
					flashMapManager.saveOutputFlashMap(flashMap, request, response);
					response.sendRedirect("/login");
				}
			});
			l.successHandler((request, response, exception) -> {
				response.sendRedirect("/");
			});
		});

		http.exceptionHandling(e -> {
			e.accessDeniedHandler((request, response, exception) -> {
				response.sendRedirect("/access_denied");
			});
		});

		http.logout((logout) -> {
			logout.logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();
		});

		return http.build();
	}

	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(authenticationProvider());

		return authenticationManagerBuilder.build();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(appUserDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
//		authProvider.setHideUserNotFoundExceptions(false);

		return authProvider;
	}
}
