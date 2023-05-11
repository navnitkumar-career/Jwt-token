package com.tokan.Config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tokan.helper.JwtUtil;
import com.tokan.servies.CustomUserDetailsServies;

@Component
public class JwtauthnticationFilter extends OncePerRequestFilter {
	
	
	
	@Autowired
	private CustomUserDetailsServies customUserDetailsServies;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		
		String header = request.getHeader("Authorization");
		String username=null;
		String  jwtToken=null;
		
		if (header!=null && header.startsWith("Bearer")) {

			jwtToken=header.substring(7);
			
			try {
				
				this.jwtUtil.getUserNameFromToken(jwtToken);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			UserDetails details = this.customUserDetailsServies.loadUserByUsername(username);
			if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(details, null,details.getAuthorities());
				
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}else {
				System.out.println("token is not valid");
			}
			
			
			
		}
			
		filterChain.doFilter(request, response);
		
	}

}
