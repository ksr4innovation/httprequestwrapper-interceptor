package com.snkit.datajpa;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class DemoRequestWrapper extends HttpServletRequestWrapper {

	private final String body;

	public DemoRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);

		InputStream stream = request.getInputStream();
		
		
		final int MAX_BODY_SIZE = 1024;
	    final StringBuilder bodyStringBuilder = new StringBuilder();
	    if (!stream.markSupported()) {
	      stream = new BufferedInputStream(stream);
	    }

	    stream.mark(MAX_BODY_SIZE + 1);
	    final byte[] entity = new byte[MAX_BODY_SIZE + 1];
	    final int bytesRead = stream.read(entity);

	    if (bytesRead != -1) {
	      bodyStringBuilder.append(new String(entity, 0, Math.min(bytesRead, MAX_BODY_SIZE)));
	      if (bytesRead > MAX_BODY_SIZE) {
	        bodyStringBuilder.append("...");
	      }
	    }
	    stream.reset();

		
		body = bodyStringBuilder.toString();
	}

	
	
	

	 @Override
	 public ServletInputStream getInputStream() throws IOException {
	   final ByteArrayInputStream byteArrayInputStream = new     ByteArrayInputStream(body.getBytes());
	   ServletInputStream servletInputStream = new ServletInputStream() {
	     public int read() throws IOException {
	       return byteArrayInputStream.read();
	     }

		@Override
		public boolean isFinished() {
			// TODO Auto-generated method stub
			return byteArrayInputStream.available() == 0;
		}

		@Override
		public boolean isReady() {
			return true;
		}

		@Override
		public void setReadListener(ReadListener listener) {
			// TODO Auto-generated method stub
			
		}
	   };
	   return servletInputStream;
	 }

	 @Override
	 public BufferedReader getReader() throws IOException {
	   return new BufferedReader(new InputStreamReader(this.getInputStream()));
	 }

	 public String getBody() {
	   return this.body;
	 }
}
