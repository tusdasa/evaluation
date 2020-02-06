package net.tusdasa.evaluation.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
@EnableCaching
public class EhCacheConfig {

    public EhCacheConfig() {
    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        ehCacheManagerFactoryBean.setConfigLocation(resolver.getResource("classpath:ehcache.xml"));
        ehCacheManagerFactoryBean.setCacheManagerName("ehCacheManager");
        ehCacheManagerFactoryBean.setShared(true);
        return ehCacheManagerFactoryBean;
    }
}
