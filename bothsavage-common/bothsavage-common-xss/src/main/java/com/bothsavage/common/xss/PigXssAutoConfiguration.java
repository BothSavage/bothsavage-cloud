

package com.bothSavage.common.xss;

import lombok.RequiredArgsConstructor;
import com.bothSavage.common.xss.core.DefaultXssCleaner;
import com.bothSavage.common.xss.core.FormXssClean;
import com.bothSavage.common.xss.core.JacksonXssClean;
import com.bothSavage.common.xss.core.XssCleaner;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * jackson xss 配置
 *
 * 
 */
@AutoConfiguration
@RequiredArgsConstructor
@EnableConfigurationProperties(com.bothSavage.common.xss.config.PigXssProperties.class)
@ConditionalOnProperty(prefix = com.bothSavage.common.xss.config.PigXssProperties.PREFIX, name = "enabled",
		havingValue = "true", matchIfMissing = true)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class PigXssAutoConfiguration implements WebMvcConfigurer {

	private final com.bothSavage.common.xss.config.PigXssProperties xssProperties;

	@Bean
	@ConditionalOnMissingBean
	public XssCleaner xssCleaner(com.bothSavage.common.xss.config.PigXssProperties properties) {
		return new DefaultXssCleaner(properties);
	}

	@Bean
	public FormXssClean formXssClean(com.bothSavage.common.xss.config.PigXssProperties properties,
			XssCleaner xssCleaner) {
		return new FormXssClean(properties, xssCleaner);
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer xssJacksonCustomizer(
			com.bothSavage.common.xss.config.PigXssProperties properties, XssCleaner xssCleaner) {
		return builder -> builder.deserializerByType(String.class, new JacksonXssClean(properties, xssCleaner));
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		List<String> patterns = xssProperties.getPathPatterns();
		if (patterns.isEmpty()) {
			patterns.add("/**");
		}
		com.bothSavage.common.xss.core.XssCleanInterceptor interceptor = new com.bothSavage.common.xss.core.XssCleanInterceptor(
				xssProperties);
		registry.addInterceptor(interceptor)
			.addPathPatterns(patterns)
			.excludePathPatterns(xssProperties.getPathExcludePatterns())
			.order(Ordered.LOWEST_PRECEDENCE);
	}

}
