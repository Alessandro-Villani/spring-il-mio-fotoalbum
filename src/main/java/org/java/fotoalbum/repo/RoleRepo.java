package org.java.fotoalbum.repo;

import org.java.fotoalbum.pojo.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
