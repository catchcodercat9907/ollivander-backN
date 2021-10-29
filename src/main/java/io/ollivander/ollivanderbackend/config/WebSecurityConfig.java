package io.ollivander.ollivanderbackend.config;

import io.ollivander.ollivanderbackend.model.ApiConst;
import io.ollivander.ollivanderbackend.security.RestAuthenticationEntryPoint;
import io.ollivander.ollivanderbackend.security.AuthTokenFilter;
import io.ollivander.ollivanderbackend.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Value("${allowed_origin}")
//    private String allowedOrigin;

//    @Autowired
//    private CORSFilter corsFilter;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private RestAuthenticationEntryPoint authEntryPointJwt;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

//    @PostConstruct
//    public void afterCreated() {
//        corsFilter.setAllowedOrigin(allowedOrigin);
//    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()// avoid CORS and CSRF(Cross Site Request Forgery)
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPointJwt)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(ApiConst.LOGIN_URL, ApiConst.PUBLIC_URL, ApiConst.LOGOUT_URL).permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/test/**").permitAll()

                // defined Admin only API area
                .antMatchers("/admin/**").hasRole("ADMIN")

                //temporary permit access /product
                .antMatchers("/product/**").permitAll()

                // all other request need to be authenticated
                .anyRequest().authenticated()
                .and()
//                .addFilterBefore(corsFilter, ChannelProcessingFilter.class)
                // logout handler
				.logout().logoutUrl(ApiConst.LOGOUT_URL).invalidateHttpSession(false);

        // When user login, role XX.
        // But access to the page requires the YY role,
        // An AccessDeniedException will be thrown.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
