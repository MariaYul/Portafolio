package com.telcel.gsrh.solicitudcurso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "com.telcel.gsrh.solicitudcurso.controller"
})
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    private static final String JSP_PREFIX = "/WEB-INF/views/";
    private static final String JSP_SUFFIX = ".jsp";
    private static final int MAX_SIZE_FILE_UPLOAD = 10485760;
    private static final int SECONDS_RESOURCES_IN_CACHE = 3600;
    private static final String[] STATIC_RESOURCES_PATH = {"/css/", "/fonts/", "/images/", "/js/", "/properties/"};
    
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix(JSP_PREFIX);
        resolver.setSuffix(JSP_SUFFIX);
        resolver.setCache(false);
        
        return resolver;
    }
    
    @Bean
    public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }
    
    @Bean(name="multipartResolver")
    public MultipartResolver commonsMultipartResolver() {
        CommonsMultipartResolver object = new CommonsMultipartResolver();
        object.setMaxInMemorySize(MAX_SIZE_FILE_UPLOAD);
        object.setMaxUploadSize(MAX_SIZE_FILE_UPLOAD);
        return object;
    }
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
            .addResourceLocations(STATIC_RESOURCES_PATH)
            .setCachePeriod(SECONDS_RESOURCES_IN_CACHE)
            .resourceChain(true)
            .addResolver(new PathResourceResolver());
    }
}
