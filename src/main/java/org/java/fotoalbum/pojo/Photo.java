package org.java.fotoalbum.pojo;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Photo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "La foto deve avere un titolo")
	private String title;
	
	@Column(columnDefinition = "text")
	@NotBlank(message = "La foto deve avere una descrizione")
	private String description;
	
	@NotBlank(message = "La foto deve avere un url dell'immagine")
	private String imageUrl;

	private Boolean visibility;
	
	@ManyToMany
	@JsonManagedReference
	private List<Category> categories;
	
	@OneToMany(mappedBy = "photo")
	@JsonManagedReference
	private List<Message> messages;
	
	public Photo() {}
	
	public Photo(String title, String description, String imageUrl, Boolean visibility) {
		
		setTitle(title);
		setDescription(description);
		setImageUrl(imageUrl);
		setVisibility(visibility);
		
	}
	
	public Photo (String title, String description, String imageUrl, Boolean visibility, Category... categories) {
		
		this(title, description, imageUrl, visibility);
		
		setCategories(categories);
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
	public Boolean getVisibility() {
		return visibility;
	}

	public void setVisibility(Boolean visibility) {
		this.visibility = visibility;
	}
	
	
	public List<Category> getCategories() {
		return categories;
	}
	
	@JsonSetter
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public void setCategories(Category[] categories) {
		
		setCategories(Arrays.asList(categories));
		
	}
	
	public void removeCategory(Category category) {
		
		categories.remove(category);
		
	}
	
	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getTitle() + ", " + getImageUrl() + ",/n" + getDescription();
		
	}
	
	
	
}
