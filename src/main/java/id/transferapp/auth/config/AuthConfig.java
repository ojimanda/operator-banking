package id.transferapp.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import id.transferapp.auth.handler.LoginSuccessHandler;
import id.transferapp.service.login.LoginUserDetail;

@Configuration
@EnableWebSecurity
public class AuthConfig {

	@Autowired
	private LoginUserDetail loginUserDetail;

	@Autowired
	private LoginSuccessHandler handler;

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(loginUserDetail);
		authProvider.setPasswordEncoder(bCryptPasswordEncoder());
		return authProvider;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeHttpRequests().requestMatchers("/admin").hasAnyAuthority("admin")
				.requestMatchers("/admin/bank/").hasAnyAuthority("admin")
				.requestMatchers("/admin/tiket").hasAnyAuthority("admin")
				.requestMatchers("/admin/bank/save").hasAnyAuthority("admin")
				.requestMatchers("/operator").hasAnyAuthority("operator")
				.requestMatchers("/operator/rekening").hasAnyAuthority("operator")
				.requestMatchers("/operator/rekening/save").hasAnyAuthority("operator")
				.requestMatchers("/cs").hasAnyAuthority("cs")
				.requestMatchers("/cs/transfer").hasAnyAuthority("cs")
				.requestMatchers("/cs/transfer/save").hasAnyAuthority("cs")
				.anyRequest().authenticated().and().formLogin().successHandler(handler).permitAll().and().logout()
				.permitAll();
		httpSecurity.csrf().disable();
		return httpSecurity.build();
	}
}
