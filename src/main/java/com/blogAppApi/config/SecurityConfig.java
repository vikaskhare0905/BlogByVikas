package com.blogAppApi.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.blogAppApi.helper.AppConstants;
import com.blogAppApi.security.CustomUserDetailsService;
import com.blogAppApi.security.JwtAuthonticationEntryPoint;
import com.blogAppApi.security.JwtAuthonticationFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String[] PUBLIC_URLS = {"/api/v1/auth/**", 
			                                    "/v3/api-docs",
			                                    "/v2/api-docs",
			                                    "/swagger_resorces/**",
			                                    "swagger_ui/**",
			                                    "/webjars/**"};

	@Autowired
	private CustomUserDetailsService customUserDetailService;

	@Autowired
	private JwtAuthonticationEntryPoint jwtAuthonticationEntryPoint;

	@Autowired
	private JwtAuthonticationFilter jwtAuthonticationFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		
		http.csrf().disable()
		.authorizeHttpRequests()
		.antMatchers(AppConstants.PUBLIC_URLS).permitAll()
		.antMatchers("/v3/api-docs").permitAll().antMatchers(HttpMethod.GET).permitAll().anyRequest()
		.authenticated()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(this.jwtAuthonticationEntryPoint)
		.and().sessionManagement()
		      .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(this.jwtAuthonticationFilter, UsernamePasswordAuthenticationFilter.class);

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}

}
