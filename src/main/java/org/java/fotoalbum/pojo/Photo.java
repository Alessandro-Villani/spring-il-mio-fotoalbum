package org.java.fotoalbum.pojo;

import java.util.Arrays;
import java.util.List;

import org.java.fotoalbum.pojo.auth.User;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
	
	@OneToMany(mappedBy = "photo", cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private List<Message> messages;
	
	@ManyToOne
	@JsonManagedReference
	private User user;
	
	@OneToMany(mappedBy = "photo", cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private List<Comment> comments;
	
	public Photo() {}
	
	public Photo(String title, String description, String imageUrl, Boolean visibility, User user) {
		
		setTitle(title);
		setDescription(description);
		setImageUrl(imageUrl);
		setVisibility(visibility);
		setUser(user);
		
	}
	
	public Photo (String title, String description, String imageUrl, Boolean visibility, User user, Category... categories) {
		
		this(title, description, imageUrl, visibility, user);
		
		setCategories(categories);
		
	}
	
	public Photo (String title, String description, String imageUrl, Boolean visibility, User user, Comment... comments) {
		
		this(title, description, imageUrl, visibility, user);
		
		setComments(comments);
		
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
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public void setComments(Comment[] comments) {
		
		setComments(Arrays.asList(comments));
		
	}
	
	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getTitle() + ", " + getImageUrl() + ",/n" + getDescription();
		
	}
	
	
	
}
