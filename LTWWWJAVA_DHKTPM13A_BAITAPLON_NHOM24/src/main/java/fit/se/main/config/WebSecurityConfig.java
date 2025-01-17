package fit.se.main.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import fit.se.main.principal.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests().antMatchers("/", "/sign-in", "/sign-out").permitAll();

		http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_MEMBER', 'ROLE_ADMIN')");

		http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");

		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		
		http.authorizeRequests().and().formLogin()
		.loginPage("/sign-in")
		.defaultSuccessUrl("/index")
		.failureUrl("/sign-in?erro=false")
		.usernameParameter("email")
		.passwordParameter("password")
		
		.and().logout().logoutUrl("/sign-out").logoutSuccessUrl("/sign-in");
		
		http.authorizeRequests().and()
		.rememberMe().tokenRepository(this.persistentTokenRepository()).tokenValiditySeconds(1*24*60*60);

		System.out.println(http.formLogin().usernameParameter("email") + " - " + http.formLogin().passwordParameter("matkhau"));

	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		
		return null;
	}

//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
//	}
}
