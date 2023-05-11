 package com.tokan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tokan.helper.JwtUtil;
import com.tokan.model.jwtrequest;
import com.tokan.model.jwtresponse;
import com.tokan.servies.CustomUserDetailsServies;

@RestController
public class JwtController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private  CustomUserDetailsServies customUserDetailsServies;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	@RequestMapping(value="/token",method = RequestMethod.POST)
	public ResponseEntity<?> token(@RequestBody jwtrequest jr) throws Exception{
		
		System.out.println(jr);
		
		
		try {
			
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jr.getUsername(), jr.getPassword()));
			
		} catch (UsernameNotFoundException e) {
			
			e.printStackTrace();
			throw new Exception("Bad credentials");
		}
		
		
		UserDetails userDetails = this.customUserDetailsServies.loadUserByUsername(jr.getUsername());
		
		String generateToken = this.jwtUtil.generateToken(userDetails);
		
		System.out.println(generateToken);
		
		return  ResponseEntity.ok(new jwtresponse(generateToken));
		
	}
	
}
