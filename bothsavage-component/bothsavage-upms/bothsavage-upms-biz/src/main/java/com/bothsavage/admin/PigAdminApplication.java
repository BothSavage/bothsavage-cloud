
package com.bothsavage.admin;

import com.bothsavage.common.feign.annotation.EnablePigFeignClients;
import com.bothsavage.common.security.annotation.EnablePigResourceServer;
import com.bothsavage.common.swagger.annotation.EnablePigDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**

 * @date 2018年06月21日
 * <p>
 * 用户统一管理系统
 */
@EnablePigDoc("admin")
@EnablePigFeignClients
@EnablePigResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class PigAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(PigAdminApplication.class, args);
	}

}
