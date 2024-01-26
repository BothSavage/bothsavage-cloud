
package com.bothSavage.common.feign;

import com.alibaba.cloud.sentinel.feign.SentinelFeignAutoConfiguration;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bothSavage.common.feign.sentinel.ext.PigSentinelFeign;
import com.bothSavage.common.feign.sentinel.handle.PigUrlBlockHandler;
import com.bothSavage.common.feign.sentinel.parser.PigHeaderRequestOriginParser;
import feign.Feign;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.TimeUnit;

/**
 * sentinel 配置
 *

 * @date 2020-02-12
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(SentinelFeignAutoConfiguration.class)
public class PigFeignAutoConfiguration {

	@Bean
	@Scope("prototype")
	@ConditionalOnMissingBean
	@ConditionalOnProperty(name = "feign.sentinel.enabled")
	public Feign.Builder feignSentinelBuilder() {
		return PigSentinelFeign.builder();
	}

	@Bean
	@ConditionalOnMissingBean
	public BlockExceptionHandler blockExceptionHandler(ObjectMapper objectMapper) {
		return new PigUrlBlockHandler(objectMapper);
	}

	@Bean
	@ConditionalOnMissingBean
	public RequestOriginParser requestOriginParser() {
		return new PigHeaderRequestOriginParser();
	}

	/**
	 * OkHttp 客户端配置
	 * @return OkHttp 客户端配
	 */
	@Bean
	public OkHttpClient okHttpClient() {
		return new OkHttpClient.Builder().retryOnConnectionFailure(false) // 是否开启缓存
			.connectTimeout(30L, TimeUnit.SECONDS) // 连接超时时间
			.readTimeout(30L, TimeUnit.SECONDS) // 读取超时时间
			.writeTimeout(30L, TimeUnit.SECONDS)
			.followRedirects(true) // 是否允许重定向
			.build();
	}

}
