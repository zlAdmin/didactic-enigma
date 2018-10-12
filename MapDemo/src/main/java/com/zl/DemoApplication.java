package com.zl;

import com.zl.demo.filter.HttpFilter;
import com.zl.demo.interceptor.HttpInterceptor;
import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @Author zhanglei
 * @Description //SpringBoot的启动类
 * @Date 23:04 2018/9/10
 * @Param
 * @return
 **/
@SpringBootApplication
public class DemoApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean httpFilter(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new HttpFilter());
		registrationBean.addUrlPatterns("/threadLocal/*");
		return registrationBean;
	}
	@Configuration
	public class ValidatorConfiguration {
		@Bean
		public Validator validator(){
			ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
					.configure()
					.addProperty( "hibernate.validator.fail_fast", "true" )
					.buildValidatorFactory();
			Validator validator = validatorFactory.getValidator();

			return validator;
		}
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
	}
}
