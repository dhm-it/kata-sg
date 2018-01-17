package com.dhm.bank.SecurityConfig;

import com.dhm.bank.clients.Client;
import com.dhm.bank.filters.CorsFilter;
import com.dhm.bank.filters.RequestsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    RestAuthenticationEntryPoint restAuthenticationEntryPoint ;
    @Autowired
    CorsFilter corsFilter ;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                if (username.equals("cid")) {
                    return new User(username, "",
                            AuthorityUtils
                                    .commaSeparatedStringToAuthorityList("ROLE_USER"));
                }
           return null;  }
        };
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/login/**");

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // disable caching
        http
                .addFilterBefore(corsFilter, ChannelProcessingFilter.class)
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .headers().cacheControl();
        //any request need to be in ssl mode
        http.requiresChannel()
                .anyRequest().requiresSecure();

        http.csrf().disable() // disable csrf for our requests.
                .authorizeRequests()
                .antMatchers("/login/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .x509()
                .subjectPrincipalRegex("CN=(.*?)(?:,|$)")
                .userDetailsService(userDetailsService())
                .and()
                // enabling the basic authentication
                .httpBasic().and()
                // configuring the session as state less. Which means there is
                // no session in the server
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // disabling the CSRF - Cross Site Request Forgery
                .csrf().disable();
    }

}
