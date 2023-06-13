package org.java.fotoalbum.controller.api;

import java.util.List;

import org.java.fotoalbum.pojo.Category;
import org.java.fotoalbum.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1")
public class CategoryApiController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategories(){
		
		List<Category> categories = categoryService.findAll();
		
		return new ResponseEntity<>(categories, HttpStatus.OK);
		
	}

}
