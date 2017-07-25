package com.example.sso.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.sso.login.annotation.SSOAnnotation;
import com.example.sso.service.IRedisService;
import com.example.sso.service.impl.RedisServiceImpl;

@Component
public class Authinterceptor  extends HandlerInterceptorAdapter{

	 @Autowired  
	  private IRedisService redisService;  
	 
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		String header = request.getHeader("cookieVal");
//		System.out.println("header:"+header);
		
		if(!(handler instanceof HandlerMethod)){
			
			return true;
		}
			
		HandlerMethod invokeObj=(HandlerMethod) handler; 
		
		SSOAnnotation methodAnnotation = invokeObj.getMethodAnnotation(SSOAnnotation.class);
		if(methodAnnotation!=null){
			String header = request.getHeader("token");
			if(header!=null){
				System.out.println(redisService);
				String string = redisService.get(header);
				
				if(string!=null){
					return true;
				}
			}else{
				//error 
				returnJson(response, "用户未登录或者授权");
				  return false;
			}
			
		}
		return true;
			
		
		
	}
	
	private void returnJson(HttpServletResponse response, String json) throws Exception{
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }
    }

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterConcurrentHandlingStarted(
			HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	}

}
