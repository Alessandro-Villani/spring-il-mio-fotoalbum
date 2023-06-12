package org.java.fotoalbum.repo;

import java.util.List;

import org.java.fotoalbum.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

	public List<Comment> findByPhotoId(int id);
	
}
