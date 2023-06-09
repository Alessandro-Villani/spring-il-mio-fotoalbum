package org.java.fotoalbum.controller;

import java.util.List;
import java.util.Optional;

import org.java.fotoalbum.pojo.Category;
import org.java.fotoalbum.pojo.Photo;
import org.java.fotoalbum.services.CategoryService;
import org.java.fotoalbum.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PhotoService photoService;
	
	@GetMapping("/auth/categories")
	public String goToCategoryIndex(Model model) {
		
		List<Category> categories = categoryService.findAll();
		
		model.addAttribute("categories", categories);
		
		return "categories-index";
		
	}
	
	@GetMapping("/auth/categories/create")
	public String createCategory(Model model) {
		
		model.addAttribute("category", new Category());
		
		return "categories-create";
		
	}
	
	@PostMapping("/auth/categories/create")
	public String storeCategory(Model model, @Valid @ModelAttribute Category category, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			
			
			model.addAttribute("category", category);
			model.addAttribute("errors", bindingResult);
			
			return "categories-create";
		}
		
		categoryService.save(category);
		
		return "redirect:/auth/categories";
		
	}
	
	@GetMapping("auth/categories/edit/{id}")
	public String editCategory(Model model, @PathVariable int id) {
		
		
		
		Optional<Category> optCategory = categoryService.findById(id);
		Category category = optCategory.get();
		
		model.addAttribute("category", category);
		
		return "categories-edit";
		
	}
	
	@PostMapping("auth/categories/update/{id}")
	public String updatePizza(Model model, @PathVariable int id, @ModelAttribute @Valid Category category, BindingResult bindingResult) {
		
		
		if(bindingResult.hasErrors()) {
			
			
			model.addAttribute("category", category);
			model.addAttribute("errors", bindingResult);
			
			return "categories-edit";
			
		}

		categoryService.save(category);
		
		return "redirect:/auth/categories";

	}
	
	@PostMapping("auth/categories/delete/{id}")
	public String deleteCategory(@PathVariable("id") int id) {
		
		Category category = categoryService.findById(id).get();
		
		for (Photo photo : category.getPhotos()) {
			
			photo.removeCategory(category);
			photoService.save(photo);
			
		}
		
		categoryService.delete(category);
		
		return "redirect:/auth/categories";
	}

}
