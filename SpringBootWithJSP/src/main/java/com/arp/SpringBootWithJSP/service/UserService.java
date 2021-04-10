package com.arp.SpringBootWithJSP.service;

import com.arp.SpringBootWithJSP.entity.*;

public interface UserService {
	public User findUserByEmail(String email);

	public void saveUser(User user);
}
