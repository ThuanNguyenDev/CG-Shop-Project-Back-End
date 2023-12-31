package com.example.case_study_m4.config;


import com.example.case_study_m4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.example.case_study_m4.entity.User user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("This" + username + "was not found in database!");
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));

		UserDetails userDetails = new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getPassword(),
				grantedAuthorities);

		return userDetails;
	}

}