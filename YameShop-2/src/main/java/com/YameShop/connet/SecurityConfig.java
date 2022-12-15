package com.YameShop.connet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.YameShop.service.CustomUserServices;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomUserServices customUserServices;
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	    return  bCryptPasswordEncoder;
	}
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
	    InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
	    return memory;
	}
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//		// Sét đặt dịch vụ để tìm kiếm User trong Database.
//		// Và sét đặt PasswordEncoder.
//	auth.userDetailsService(customUserServices).passwordEncoder(passwordEncoder());
//
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin/login", "/logout" ,"/Home" ,"/adimin/assets/js/**" , "/admin/css/size.css" , "/view/js/**" ).permitAll();

		// Trang /userInfo yêu cầu phải login với vai trò ROLE_USER hoặc ROLE_ADMIN.
		// Nếu chưa login, nó sẽ redirect tới trang /login.
//		http.authorizeRequests().antMatchers("/admin/import_coupon").access("hasAnyRole('Nhân viên kho')");
		
		// Trang chỉ dành cho ADMIN
		http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
		
		
		// Khi người dùng đã login, với vai trò XX.
		// Nhưng truy cập vào trang yêu cầu vai trò YY,
		// Ngoại lệ AccessDeniedException sẽ ném ra.
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		http.authorizeRequests().and().formLogin()//
		// Submit URL của trang login
		
		.loginProcessingUrl("/j_spring_security_check") // Submit URL
		.loginPage("/admin/login")//
		.defaultSuccessUrl("/admin/dashboard")//
		.failureUrl("/admin/login")//
		.usernameParameter("username")//
		.passwordParameter("password")
		.and().csrf().disable().cors()
		// Cấu hình cho Logout Page.
		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/admin/logoutSuccessful");
		
//		http.authorizeRequests().antMatchers("/admin/import_coupon/**").access("hasRole('Nhân viên kho')");
//		http.authorizeRequests().and().formLogin()//
//		.loginProcessingUrl("/j_spring_security_check") // Submit URL
//		.loginPage("/admin/login")//
//		.defaultSuccessUrl("/admin/import_coupon/add")//
//		.failureUrl("/admin/login")//
//		.usernameParameter("username")//
//		.passwordParameter("password")
//		.and().csrf().disable().cors()
//		// Cấu hình cho Logout Page.
//		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/admin/logoutSuccessful");
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserServices).passwordEncoder(passwordEncoder());
	}
}