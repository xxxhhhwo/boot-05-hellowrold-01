package com.atguigu.boot.conf;

import com.atguigu.boot.bean.Pet;
import com.atguigu.boot.convertor.MyAppConvertor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.accept.AbstractMappingContentNegotiationStrategy;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.util.*;

/**
 * 设置当前配置文件里的bean没有依赖关系
 */
@Configuration(proxyBeanMethods = false)
public class ApplicationConf  /*implements WebMvcConfigurer */{

//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        UrlPathHelper pathHelper=new UrlPathHelper();
//        pathHelper.setRemoveSemicolonContent(false);
//        configurer.setUrlPathHelper(pathHelper);
//    }
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webMvcConfigurer=new WebMvcConfigurer() {

            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                Map<String, MediaType> map=new HashMap<>();
                map.put("json",MediaType.APPLICATION_JSON);
                map.put("xml",MediaType.APPLICATION_XML);
                map.put("gg", MediaType.parseMediaType("application/x-ssq"));


                HeaderContentNegotiationStrategy headerContentNegotiationStrategy=new HeaderContentNegotiationStrategy();
                ParameterContentNegotiationStrategy strategy=new ParameterContentNegotiationStrategy(map);
                configurer.strategies(Arrays.asList(strategy,headerContentNegotiationStrategy));

            }

            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new MyAppConvertor());
            }

            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper pathHelper=new UrlPathHelper();
                pathHelper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(pathHelper);
            }

            @Override
            public void addFormatters(FormatterRegistry registry) {
                registry.addConverter(new Converter<String, Pet>() {
                    @Override
                    public Pet convert(String source) {
                        if(!StringUtils.isEmpty(source)){
                            Pet pet=new Pet();
                            String[] split = source.split(",");
                            pet.setName(split[0]);
                            pet.setAge(Integer.parseInt(split[1]));
                            return pet;
                        }
                        return null;
                    }
                });
            }
        };
        return  webMvcConfigurer;
    }

    //因为配置文件里写的是 如果没有配置 那么他自动给配置  如果自己手动配置了  那么就用自己的

    /**
     * 	@ConditionalOnMissingBean(HiddenHttpMethodFilter.class)
     *
     * 是指 如果 HiddenHttpMethodFilter 这个bean不存在
     */

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        HiddenHttpMethodFilter methodFilter=new HiddenHttpMethodFilter();
        methodFilter.setMethodParam("_mm");
        return methodFilter;
    }

}
