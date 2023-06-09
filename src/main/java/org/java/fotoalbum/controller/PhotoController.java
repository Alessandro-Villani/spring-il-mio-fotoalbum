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
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class PhotoController {

	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/")
	public String goToHomePage(Model model) {
		
		List<Photo> photos = photoService.findAll();
		
		model.addAttribute("photos", photos);
		
		return "home";
		
	}
	
	@PostMapping("/")
	public String homeSearch(Model model, @RequestParam(required = false) String title) {
		
		List<Photo> photos = photoService.findByTitleContaining(title);
		
		model.addAttribute("photos", photos);
		model.addAttribute("searchTerm", title);
		
		return "home";
		
	}
	
	@GetMapping("/{id}")
	public String goToPhotoDetails(Model model, @PathVariable("id") int id) {
		
		Optional<Photo> optPhoto = photoService.findById(id);
		
		Photo photo = optPhoto.get();
		
		model.addAttribute("photo", photo);
		
		return "photo-details";
	}
	
	@GetMapping("auth/photos/create")
	public String createPhoto(Model model) {
		
		List<Category> categories = categoryService.findAll();
		
		model.addAttribute("photo", new Photo());
		model.addAttribute("categories", categories);
		
		return "photo-create";
		
	}
	
	@PostMapping("auth/photos/create")
	public String storePhoto(Model model, @Valid @ModelAttribute Photo photo, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			List<Category> categories = categoryService.findAll();
			
			model.addAttribute("photo", photo);
			model.addAttribute("errors", bindingResult);
			model.addAttribute("categories", categories);
			
			return "photo-create";
		}
		
		photoService.save(photo);
		
		return "redirect:/";
		
	}
	
	@GetMapping("auth/photos/edit/{id}")
	public String editPhoto(Model model, @PathVariable int id) {
		
		
		
		Optional<Photo> optPhoto = photoService.findById(id);
		Photo photo = optPhoto.get();
		
		List<Category> categories = categoryService.findAll();
		
		model.addAttribute("photo", photo);
		model.addAttribute("categories", categories);
		
		return "photo-edit";
		
	}
	
	@PostMapping("auth/photos/update/{id}")
	public String updatePhoto(Model model, @PathVariable int id, @ModelAttribute @Valid Photo photo, BindingResult bindingResult) {
		
		
		if(bindingResult.hasErrors()) {
			
			List<Category> categories = categoryService.findAll();
			
			model.addAttribute("photo", photo);
			model.addAttribute("errors", bindingResult);
			model.addAttribute("categories", categories);
			
			return "photo-edit";
			
		}

		photoService.save(photo);
		
		return "redirect:/" + id;

	}
	
	@PostMapping("auth/photos/delete/{id}")
	public String deletePhoto(@PathVariable("id") int id) {
		
		Photo photo = photoService.findById(id).get();
		
		
		photoService.delete(photo);
		
		return "redirect:/";
	}
	
	
	
	
}
