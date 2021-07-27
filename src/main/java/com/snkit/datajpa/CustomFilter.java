package com.snkit.datajpa;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@WebFilter
@Component
public class CustomFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("  From CustomFilter doFilterInternal");
		DemoRequestWrapper requestWrapper = new DemoRequestWrapper(request);
		System.out.println("  From CustomFilter doFilterInternal   request body value  " + requestWrapper.getBody());
		
		filterChain.doFilter(requestWrapper, response);

	}

}
