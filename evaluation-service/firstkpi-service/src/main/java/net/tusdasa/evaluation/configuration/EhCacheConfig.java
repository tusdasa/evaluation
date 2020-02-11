package net.tusdasa.evaluation.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class EhCacheConfig {

    public EhCacheConfig() {
    }
/*
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        ehCacheManagerFactoryBean.setConfigLocation(resolver.getResource("classpath:ehcache.xml"));
        ehCacheManagerFactoryBean.setCacheManagerName("ehCacheManager");
        ehCacheManagerFactoryBean.setShared(true);
        return ehCacheManagerFactoryBean;
    }
 */
}
