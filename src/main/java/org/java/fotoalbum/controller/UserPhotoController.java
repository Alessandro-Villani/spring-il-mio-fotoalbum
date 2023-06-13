package org.java.fotoalbum.controller;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.java.fotoalbum.pojo.Category;
import org.java.fotoalbum.pojo.Photo;
import org.java.fotoalbum.pojo.auth.User;
import org.java.fotoalbum.services.CategoryService;
import org.java.fotoalbum.services.PhotoService;
import org.java.fotoalbum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class UserPhotoController {
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/user/auth")
	public String goToMyPhotos(Model model, Principal principal) {
		
		String userName = principal.getName();
		
		User user = (User) userService.loadUserByUsername(userName);
		
		List<Photo> photos = user.getPhotos();
		
		model.addAttribute("photos", photos);
		
		return "my-photos";
		
	}
	
	@PostMapping("/user/auth")
	public String homeSearch(Model model, @RequestParam(required = false) String title, Principal principal) {
		
		String userName = principal.getName();
		
		User user = (User) userService.loadUserByUsername(userName);
		
		List<Photo> photos = photoService.findByTitleContaining(title).stream().filter(photo -> photo.getUser().getId() == user.getId()).collect(Collectors.toList());
		
		model.addAttribute("photos", photos);
		model.addAttribute("searchTerm", title);
		
		return "my-photos";
		
	}
	
	@GetMapping("user/auth/photos/create")
	public String createPhoto(Model model) {
		
		List<Category> categories = categoryService.findAll();
		
		model.addAttribute("photo", new Photo());
		model.addAttribute("categories", categories);
		
		return "photo-create";
		
	}
	
	@PostMapping("user/auth/photos/create")
	public String storePhoto(Model model, @Valid @ModelAttribute Photo photo, BindingResult bindingResult, Principal principal) {
		
		String userName = principal.getName();
		
		User user = (User) userService.loadUserByUsername(userName);
		
		photo.setUser(user);
		
		if(bindingResult.hasErrors()) {
			List<Category> categories = categoryService.findAll();
			
			model.addAttribute("photo", photo);
			model.addAttribute("errors", bindingResult);
			model.addAttribute("categories", categories);
			
			return "photo-create";
		}
		
		photoService.save(photo);
		
		return "redirect:/user/auth";
		
	}
	
	@GetMapping("user/auth/photos/edit/{id}")
	public Object editPhoto(Model model, @PathVariable int id, Principal principal) throws AccessDeniedException{
		
		String userName = principal.getName();
		
		User user = (User) userService.loadUserByUsername(userName);
		
		
		Optional<Photo> optPhoto = photoService.findById(id);
		Photo photo = optPhoto.get();
		
		if(!user.getPhotos().contains(photo) || photo.getVisibility() == false) {
			
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			
		}
		
		
		List<Category> categories = categoryService.findAll();
		
		model.addAttribute("photo", photo);
		model.addAttribute("categories", categories);
		
		return "photo-edit";
		
	}
	
	@PostMapping("user/auth/photos/update/{id}")
	public String updatePhoto(Model model, @PathVariable int id, @ModelAttribute @Valid Photo photo, BindingResult bindingResult) {
		
		System.out.println(photo.getMpImage());
		System.out.println(bindingResult);
		
		if(photo.getMpImage() == null || photo.getMpImage().isEmpty()) {
			Optional<Photo> optPhoto = photoService.findById(id);
			Photo existingPhoto = optPhoto.get();
			photo.setImage(existingPhoto.getImage());
		}
		
		
		if(bindingResult.hasErrors() && !bindingResult.hasFieldErrors("imagePresent")) {
			
			List<Category> categories = categoryService.findAll();
			
			model.addAttribute("photo", photo);
			model.addAttribute("errors", bindingResult);
			model.addAttribute("categories", categories);
			
			return "photo-edit";
			
		}
		
		
		photoService.save(photo);
		
		return "redirect:/" + id;

	}
	
	@PostMapping("user/auth/photos/delete/{id}")
	public Object deletePhoto(@PathVariable("id") int id, Principal principal) {
		
		String userName = principal.getName();
		
		User user = (User) userService.loadUserByUsername(userName);
		
		Photo photo = photoService.findById(id).get();
		
		if(user.getPhotos().contains(photo)) {
			photoService.delete(photo);
			
			return "redirect:/user/auth";
		} else {
			
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			
		}
		
		
	}
	
	@GetMapping("user/auth/photos/{id}/comments")
	public Object getComments(@PathVariable("id") int id, Principal principal, Model model) {
		
		String userName = principal.getName();
		
		User user = (User) userService.loadUserByUsername(userName);
		
		Photo photo = photoService.findById(id).get();
		
		if(user.getPhotos().contains(photo)) {
			model.addAttribute("photo", photo);
			
			return "comments";
		} else {
			
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			
		}
	}
	
	
}
