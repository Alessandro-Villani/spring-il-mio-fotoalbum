package org.java.fotoalbum.services;

import java.util.List;
import java.util.Optional;

import org.java.fotoalbum.pojo.Photo;
import org.java.fotoalbum.repo.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {
	
	@Autowired
	private PhotoRepo photoRepo;
	
	public List<Photo> findAll(){
		
		return photoRepo.findAll();
		
	}
	
	public Optional<Photo> findById(int id) {
		
		return photoRepo.findById(id);
	}
	
	public List<Photo> findByTitleContaining(String title){
		
		return photoRepo.findByTitleContaining(title);
		
	}
	
	public List<Photo> findByVisibility(Boolean visibility){
		
		return photoRepo.findByVisibility(visibility);
	}
	
	public Photo save(Photo photo) {
		
		return photoRepo.save(photo);
		
	}
	
	public void delete(Photo photo) {
		
		photoRepo.delete(photo);
		
	}

}
