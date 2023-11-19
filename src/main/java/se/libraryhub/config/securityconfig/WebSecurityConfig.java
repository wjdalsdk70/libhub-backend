package se.libraryhub.config.securityconfig;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.DisableEncodeUrlFilter;
import se.libraryhub.config.filter.FakeAuthenticationFilter;
import se.libraryhub.config.oauth.CustomOAuth2UserService;
import se.libraryhub.config.oauth.PrincipalDetails;
import se.libraryhub.user.domain.Role;
import se.libraryhub.user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService oauthUserService;
//    private final UserService userService;

//    @Bean
//    public FakeAuthenticationFilter fakeAuthenticationFilter(){
//        return new FakeAuthenticationFilter(userService);
//    }

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
            "/swagger-ui/**"
            // other public endpoints of your API may be appended to this array
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/api/user/login")
                .permitAll()
                .antMatchers("/api/user/**")
                .hasAnyRole(Role.USER.name(),Role.GUEST.name(),Role.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .cors(Customizer.withDefaults())
                .formLogin().disable();
        http
                .oauth2Login()
                .userInfoEndpoint()
                .userService(oauthUserService);

//                .and()
//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                        System.out.println(request.toString());
//                    }
//                });
    }


}
