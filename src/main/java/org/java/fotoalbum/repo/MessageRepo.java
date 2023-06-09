package org.java.fotoalbum.repo;

import org.java.fotoalbum.pojo.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Integer>{

}
