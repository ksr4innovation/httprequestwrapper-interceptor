package com.snkit.datajpa;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
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
		
		System.out.println("  execution time Interceptor added From LoggingInterceptor preHandle HandlerInterceptor");
		
		DemoRequestWrapper requestWrapper = new DemoRequestWrapper(request);
		
		System.out.println("  From LoggingInterceptor preHandle   request body value  "+requestWrapper.getBody());
		String requestId = UUID.randomUUID().toString();
		long startTime = System.currentTimeMillis();
	    request.setAttribute("startTime", startTime);
	    request.setAttribute("requestId", requestId);

		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = mapper.readValue(requestWrapper.getBody(), Map.class);

		Optional<EmpEntity> empEntityOptional = empRepository.findByName(map.get("name"));

		if (empEntityOptional.isPresent()) {
                  System.out.println("Employee has privilege ");
			return true;
		} else {
			System.out.println("Employee is having  Insufficient privilege  ");
			throw new Exception("Insufficient privilege ");
		}

	}

	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		
		System.out.println("  From LoggingInterceptor postHandle  HandlerInterceptor");
		
		 long startTime = (Long)request.getAttribute("startTime");    
		    long endTime = System.currentTimeMillis();
		    long executeTime = endTime - startTime;
		    
		    System.out.println("  From LoggingInterceptor execution time taken end point "
		    +request.getRequestURL()+"  execution time   "+executeTime);
	}

}
