package org.java.fotoalbum.services;

import java.util.List;
import java.util.Optional;

import org.java.fotoalbum.pojo.Comment;
import org.java.fotoalbum.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

	@Autowired
	private CommentRepo commentRepo;
	
	public List<Comment> findAll(){
		
		return commentRepo.findAll();
		
	}
	
	public Optional<Comment> findById(int id){
		
		return commentRepo.findById(id);
		
	}
	
	public List<Comment> findByPhotoId(int id){
		
		return commentRepo.findByPhotoId(id);
		
	}
	
	public Comment save(Comment comment) {
		
		return commentRepo.save(comment);
		
	}
	
	public void delete(Comment comment) {
		
		commentRepo.delete(comment);
		
	}
	
}
