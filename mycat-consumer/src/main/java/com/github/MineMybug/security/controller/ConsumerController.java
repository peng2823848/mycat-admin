/**   
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2017</p>    
* @date 2018年10月19日  
* @version 1.0  
*/
package com.github.MineMybug.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.MineMybug.security.fiegnclient.IProviderFeign;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**   
* <p>Description: </p>  
* @author ruanhang  
* @date 2018年10月19日  
*/
@Api(value="/test",tags="测试swagger")
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
	
	@Value("${server.port}")
	private String port;
	
	@Autowired
	private IProviderFeign iProviderFeign;
	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String hello(){
		return iProviderFeign.provider();
	}
	
	@ApiOperation(value="获取端口号",notes="获取端口号")
	@RequestMapping(value="/testSwagger",method=RequestMethod.GET)
	public String testSwagger(){
		return "i am " + port;
	}

}
