package com.example.sso.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.sso.core.Authinterceptor;

/**
 * 
 *
 */
@SpringBootApplication
@ComponentScan({"com.example.sso.web","com.example.sso.core","com.example.sso.service.impl"})
public class SsoOrderApplication extends WebMvcConfigurerAdapter  implements EmbeddedServletContainerCustomizer{

	public static void main(String[] args) {
		SpringApplication.run(SsoOrderApplication.class, args);
	}

	  private CorsConfiguration buildConfig() {  
	        CorsConfiguration corsConfiguration = new CorsConfiguration();  
	        corsConfiguration.addAllowedOrigin("*");  
	        corsConfiguration.addAllowedHeader("*");  
	        corsConfiguration.addAllowedMethod("*");  
	        return corsConfiguration;  
	    }  
	      
	    /** 
	     * 跨域过滤器 
	     * @return 
	     */  
	    @Bean  
	    public CorsFilter corsFilter() {  
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();  
	        source.registerCorsConfiguration("/**", buildConfig()); // 4  
	        return new CorsFilter(source);  
	    }

		@Override
		public void customize(ConfigurableEmbeddedServletContainer arg0) {
			// TODO Auto-generated method stub
			arg0.setPort(8989);
		}  
		
		/**
		 * 拦截器配置
		 */
		@Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        // 多个拦截器组成一个拦截器链
	        // addPathPatterns 用于添加拦截规则
	        // excludePathPatterns 用户排除拦截
	        registry.addInterceptor(new Authinterceptor()).addPathPatterns("/**");
	        super.addInterceptors(registry);
	    }
}
