package com.snkit.datajpa;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoggingInterceptor implements HandlerInterceptor {
	
	@Autowired
	EmpRepository empRepository;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("  From LoggingInterceptor preHandle");
		
		DemoRequestWrapper requestWrapper = new DemoRequestWrapper(request);
		
		System.out.println("  start time added in request From LoggingInterceptor preHandle   request body value  "+requestWrapper.getBody());
		String requestId = UUID.randomUUID().toString();
		long startTime = System.currentTimeMillis();
	    request.setAttribute("startTime", startTime);
	    request.setAttribute("requestId", requestId);
		

		return true;
	}

	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		
		System.out.println("  From LoggingInterceptor postHandle");
		
		 long startTime = (Long)request.getAttribute("startTime");    
		    long endTime = System.currentTimeMillis();
		    long executeTime = endTime - startTime;
		    
		    System.out.println("  Computing method execution time From LoggingInterceptor execution time taken end point "
		    +request.getRequestURL()+"  execution time   "+executeTime);
	}

}
