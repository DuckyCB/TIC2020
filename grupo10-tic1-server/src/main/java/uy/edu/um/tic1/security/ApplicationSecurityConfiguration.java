package uy.edu.um.tic1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import uy.edu.um.tic1.entities.users.AppUser;
import uy.edu.um.tic1.security.user.ApplicationUserRole;
import uy.edu.um.tic1.security.user.ApplicationUserService;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ApplicationUserService applicationUserService;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index", "/admin/create/","/admin/register/client/", "/admin/user/", "/products/", "/size-color/", "/brands/", "/stores/" ).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
//                .formLogin()
//                        .loginProcessingUrl("/login")
//                            .permitAll()
//                            .defaultSuccessUrl("/")
//                            .passwordParameter("password")
//                            .usernameParameter("username")
//                    .and()
//                        .logout()
//                            .logoutUrl("/logout")
//                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // https://docs.spring.io/spring-security/site/docs/4.2.12.RELEASE/apidocs/org/springframework/security/config/annotation/web/configurers/LogoutConfigurer.html
//                            .clearAuthentication(true)
//                            .invalidateHttpSession(true)
//                            .deleteCookies("JSESSIONID")
//                            .logoutSuccessUrl("/");

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(applicationUserService);
        return daoAuthenticationProvider;
    }




}
