package com.example.app1.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
	// @ServiceをつけることによってspringのDIコンテナの管理下にすることができる
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.identifyUser(username);
		
		// User情報が取得できなかった場合、のエラーハンドリング
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return user;
	}
}
