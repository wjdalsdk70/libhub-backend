package se.libraryhub.security.securityconfig;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import se.libraryhub.user.service.UserService;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    private final UserService userService;

//    @Bean
//    public FilterRegistrationBean<FakeAuthenticationFilter> filter(){
//        FilterRegistrationBean<FakeAuthenticationFilter> bean = new FilterRegistrationBean<>(new FakeAuthenticationFilter(userService));
//        return bean;
//    }
}
