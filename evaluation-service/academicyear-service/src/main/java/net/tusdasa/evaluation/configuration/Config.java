package net.tusdasa.evaluation.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    public Config() {
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
