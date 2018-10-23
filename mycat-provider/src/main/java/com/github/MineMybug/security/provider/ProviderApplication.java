/**   
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2017</p>    
* @date 2018年10月18日  
* @version 1.0  
*/
package com.github.MineMybug.security.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**   
* <p>Description: </p>  
* @author ruanhang  
* @date 2018年10月18日  
*/
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.github.MineMybug.security.provider"})
public class ProviderApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProviderApplication.class, args);
	}

}
