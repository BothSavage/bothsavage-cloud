
package com.cadillac.common.security.annotation;

import com.cadillac.common.security.component.PigResourceServerAutoConfiguration;
import com.cadillac.common.security.component.PigResourceServerConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.lang.annotation.*;

/**

 * @date 2022-06-04
 * <p>
 * 资源服务注解
 */
@Documented
@Inherited
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ PigResourceServerAutoConfiguration.class, PigResourceServerConfiguration.class })
public @interface EnablePigResourceServer {

}
