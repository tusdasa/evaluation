package net.tusdasa.evaluation.config;

import net.tusdasa.evaluation.interceptor.RoleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author: tusdasa
 * @Date: 2020-03-19 5:22 PM
 */

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    private RoleInterceptor roleInterceptor;

    public InterceptorConfig(RoleInterceptor roleInterceptor) {
        this.roleInterceptor = roleInterceptor;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(roleInterceptor)
                .addPathPatterns("/**").order(1);
        super.addInterceptors(registry);
    }

}
