package com.example.sso.web;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sso.bean.ResponseBean;
import com.example.sso.service.IRedisService;

@RestController
public class UserController {

	 @Autowired  
	  private IRedisService redisService;  
	 
	 
	 @RequestMapping(value="/login",method=RequestMethod.POST)
	 public ResponseBean redisSet(@RequestParam("name")String name,@RequestParam("pass")String pass){  
		 String string = UUID.randomUUID().toString();
	        boolean isOk = redisService.set(string, name);  
	        ResponseBean  bean=new ResponseBean();
	        if(isOk){
	        
	    		 Map<String, Object> map= new  HashMap<>();
	    		 map.put("cookieValue", string);
	    		 bean.setMsg("success");
	    		 bean.setCode("1");
	    		 bean.setObj(map);
	    	        
	        }else{

	        	bean.setMsg("fail");
	    		 bean.setCode("1");
		 
	    }  
	        return bean;
	 
	 }
	 
	 }
