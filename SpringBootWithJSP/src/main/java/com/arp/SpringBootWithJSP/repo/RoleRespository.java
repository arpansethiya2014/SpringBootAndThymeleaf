package com.arp.SpringBootWithJSP.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arp.SpringBootWithJSP.entity.*;

@Repository("roleRepository")
public interface RoleRespository extends JpaRepository<Role, Long> {

	Role findByRole(String role);

}
