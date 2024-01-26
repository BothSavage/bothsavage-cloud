
package com.bothSavage.auth;

import com.bothSavage.common.feign.annotation.EnablePigFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**

 * @date 2018年06月21日 认证授权中心
 */
@EnablePigFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class PigAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(PigAuthApplication.class, args);
	}

}
