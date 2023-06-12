package org.java.fotoalbum.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.java.fotoalbum.pojo.Photo;
import org.java.fotoalbum.pojo.auth.User;
import org.java.fotoalbum.services.PhotoService;
import org.java.fotoalbum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PhotoController {

	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String goToHome(Model model, Principal principal) {
		
		if(principal != null) {
			
			String userName = principal.getName();
			model.addAttribute("userName", userName);
		} else model.addAttribute("username", null);
		
		return "home";
	}
	
	@GetMapping("admin/auth/moderation")
	public String goToModerationPage(Model model) {
		
		List<Photo> photos = photoService.findAll();
		
		model.addAttribute("photos", photos);
		
		return "moderation";
		
	}
	
	@PostMapping("admin/auth/moderation")
	public String moderationSearch(Model model, @RequestParam(required = false) String title) {
		
		List<Photo> photos = photoService.findByTitleContaining(title);
		
		model.addAttribute("photos", photos);
		model.addAttribute("searchTerm", title);
		
		return "moderation";
		
	}
	
	@GetMapping("/{id}")
	public String goToPhotoDetails(Model model, @PathVariable("id") int id, Principal principal) {
		
		if(principal != null) {
			String userName = principal.getName();
			
			User user = (User) userService.loadUserByUsername(userName);
			
			model.addAttribute("userId", user.getId());
		} else model.addAttribute("userId", -1);
		
		
		Optional<Photo> optPhoto = photoService.findById(id);
		
		Photo photo = optPhoto.get();
		
		model.addAttribute("photo", photo);
		
		return "photo-details";
	}
	
	@PostMapping("admin/auth/photos/{id}/toggle-visibility")
	public String toggleVisibility(@PathVariable("id") int id) {
		
		Optional<Photo> optPhoto = photoService.findById(id);
		
		Photo photo = optPhoto.get();
		
		if(photo.getVisibility() == null) photo.setVisibility(true);
		else photo.setVisibility(!photo.getVisibility());
		
		photoService.save(photo);
		
		return "redirect:/admin/auth/moderation";
		
	}
	
	
	
}
