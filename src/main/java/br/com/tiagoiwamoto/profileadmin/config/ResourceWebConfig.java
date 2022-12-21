package br.com.tiagoiwamoto.profileadmin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceWebConfig implements WebMvcConfigurer {
    final Environment environment;

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS =
            {
                    "file:./files"
            };

    public ResourceWebConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }
}
