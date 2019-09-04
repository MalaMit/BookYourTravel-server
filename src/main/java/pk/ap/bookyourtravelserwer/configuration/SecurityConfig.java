package pk.ap.bookyourtravelserwer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pk.ap.bookyourtravelserwer.security.JwtFilter;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

//      No session in our app
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/auth/signin").permitAll()
                .antMatchers("/users/create").permitAll()
                .antMatchers("/travel/search").permitAll()
                .antMatchers("/travel/ticketBuy").permitAll()
                .anyRequest().authenticated();


        // todo this can be good. Instead setting in UI that if user isn't logged, login page should appear,
        // this can be done here - need of testing
//        http.exceptionHandling().accessDeniedPage("/login");


        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
