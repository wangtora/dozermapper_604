package jp.co.wangtora;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public DozerBeanMapper makeDozerBeanMapper() {

		return new DozerBeanMapper();
	}
}