package se.libraryhub.security.securityconfig;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import se.libraryhub.security.filter.CustomAuthFailureHandler;
import se.libraryhub.security.filter.FakeAuthenticationFilter;
import se.libraryhub.security.oauth.CustomOAuth2UserService;
import se.libraryhub.user.domain.Role;
import se.libraryhub.user.service.UserService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService oauthUserService;
    private final CustomAuthFailureHandler customAuthFailureHandler;
    private final UserService userService;

    @Bean
    public FakeAuthenticationFilter fakeAuthenticationFilter(){
        return new FakeAuthenticationFilter(userService);
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers(AUTH_WHITELIST);
    }

    private final String[] SWAGGER = {
            "/v3/api-docs",
            "/swagger-resources/**", "/configuration/security", "/webjars/**",
            "/swagger-ui.html", "/swagger/**", "/swagger-ui/**"
    };

    private static final String[] AUTH_WHITELIST = {
            // all
            "/**",
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            // other public endpoints of your API may be appended to this array
            "/api/project/**",
            "/api/library/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/api/user/**")
                .hasAnyRole(Role.USER.name(),Role.GUEST.name(),Role.ADMIN.name())
                .anyRequest().authenticated().and()
                .cors(Customizer.withDefaults())
                .formLogin().loginPage("http://localhost:3000/auth");
        http
                .oauth2Login()
                .userInfoEndpoint()
                .userService(oauthUserService).and()
                .failureHandler(customAuthFailureHandler);
    }


}
