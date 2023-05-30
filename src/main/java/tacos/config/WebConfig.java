package tacos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Yongjian Bai
 * @since 2023/05/30
 **/

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers (ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }
}
