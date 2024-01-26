
package com.cadillac.common.swagger.annotation;

import com.cadillac.common.swagger.config.OpenAPIDefinitionImportSelector;
import com.cadillac.common.swagger.support.SwaggerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启 cadillac spring doc
 *

 * @date 2022-03-26
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableConfigurationProperties(SwaggerProperties.class)
@Import({ OpenAPIDefinitionImportSelector.class })
public @interface EnablePigDoc {

	/**
	 * 网关路由前缀
	 * @return String
	 */
	String value();

}
