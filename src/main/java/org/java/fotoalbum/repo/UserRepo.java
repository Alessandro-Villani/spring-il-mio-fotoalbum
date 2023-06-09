package org.java.fotoalbum.repo;

import java.util.Optional;

import org.java.fotoalbum.pojo.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	public Optional<User> findByUsername(String username);

}
