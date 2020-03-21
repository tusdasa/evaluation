package net.tusdasa.evaluation.configuration;

import mathutils.MathUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: tusdasa
 * @Date: 2020-03-21 7:56 PM
 */

@Configuration
public class MathUtilsConfig {

    public MathUtilsConfig() {
    }

    @Bean
    public MathUtils mathUtils() {
        return new MathUtils();
    }
}
