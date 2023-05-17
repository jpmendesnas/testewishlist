package com.wishlist.app.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {



	private static final String [] PUBLIC_MATCHERS = {
			"/login/**",
			"/swagger-ui*"
	};

	private static final String [] PUBLIC_MATCHERS_GET = {
			"/login/**",
			"/**"
	};

	private static final String [] PUBLIC_MATCHERS_POST = {
			"/login/**",
			"/**"
	};

	private static final String [] PUBLIC_MATCHERS_PUT = {
			"/**"
	};

	private static final String [] PUBLIC_MATCHERS_DELETE = {
			"/login/**",
			"/**"
	};

	private static final String [] PUBLIC_MATCHERS_PATCH = {
			"/login/**",
			"/**"
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.authorizeRequests().
				antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll().
				antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll().
				antMatchers(HttpMethod.PUT, PUBLIC_MATCHERS_PUT).permitAll().
				antMatchers(HttpMethod.DELETE, PUBLIC_MATCHERS_DELETE).permitAll().
				antMatchers(HttpMethod.PATCH, PUBLIC_MATCHERS_PATCH).permitAll().
				antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();


		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs",
				"/configuration/ui",
				"/swagger-resources/**",
				"/configuration/security",
				"/swagger-ui.html",
				"/webjars/**");
	}


	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {


		CorsConfiguration configuration = new CorsConfiguration();
//	    configuration.setAllowCredentials(true);
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.setAllowedHeaders(Arrays.asList("*"));

//	     This allow us to expose the headers
		configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
				"Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers", "Corporativo", "url"));

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
//		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

	@Bean
	@Lazy
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

