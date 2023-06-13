package org.java.fotoalbum.controller.api;

import java.util.List;
import java.util.stream.Collectors;

import org.java.fotoalbum.pojo.Category;
import org.java.fotoalbum.pojo.Photo;
import org.java.fotoalbum.services.CategoryService;
import org.java.fotoalbum.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class PhotoApiController {

	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/photos")
	public ResponseEntity<List<Photo>> getAllPhoto(@RequestParam(required = false) String title, @RequestParam(required = false) Integer categoryId){
		
		if(title != null && !title.isEmpty()) {
			
			List<Photo> photoList = photoService.findByTitleContaining(title);
			photoList = photoList.stream()
					.filter(photo -> photo.getVisibility() != null && photo.getVisibility() == true)
					.collect(Collectors.toList());
			if(categoryId != null) {
				
				Category category = categoryService.findById(categoryId).get();
				photoList = photoList.stream()
						.filter(photo -> photo.getCategories().contains(category))
						.collect(Collectors.toList());
				return new ResponseEntity<>(photoList, HttpStatus.OK);
				
			}
			return new ResponseEntity<>(photoList, HttpStatus.OK);
			
		}
		
		if(categoryId != null) {
			
			List<Photo> photoList = photoService.findByVisibility(true);
			Category category = categoryService.findById(categoryId).get();
			photoList = photoList.stream()
					.filter(photo -> photo.getCategories().contains(category))
					.collect(Collectors.toList());
			return new ResponseEntity<>(photoList, HttpStatus.OK);
			
		}
		
			List<Photo> photoList = photoService.findByVisibility(true);
			
			return new ResponseEntity<>(photoList, HttpStatus.OK);
		

		
	}
	
}
