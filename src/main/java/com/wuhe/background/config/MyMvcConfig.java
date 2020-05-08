package com.wuhe.background.config;

import com.wuhe.background.component.LoginHandlerInterceptor;
import com.wuhe.background.component.MyLocaleResolver;
import com.wuhe.background.component.StringToEventConverterV2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wuhe
 * @create 2019/9/27 0027-下午 12:05
 * 使用WebMvcConfigurer可以来扩展springMVC的功能
 */
//@EnableWebMvc完全接管 springMVC自动配置不生效
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Bean //所有的WebMvcConfigurer都会一起起作用
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }
        };
        return webMvcConfigurer;
    }

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index.html", "/", "/user/**", "/asset/**", "/webjars/**",
                        "/getAllMarker", "/getAllEvent", "/submitEvent", "/getChartsInfo", "/batchImportEvents/**");
    }

    //    SpringBoot查看容器中是否含有localeResolver，没有就自己创建localeResolver，这就是它默认的解析器。
//    这里使用自定义的国际化解析器注入容器，就不会使用默认的了
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    @Bean
    public StringToEventConverterV2 stringToEventConverterV2() {
        return new StringToEventConverterV2();
    }
}
