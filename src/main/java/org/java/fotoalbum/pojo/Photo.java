package org.java.fotoalbum.pojo;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.java.fotoalbum.pojo.auth.User;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
	
	@Lob
	@Column(length=16777215)
	@JsonIgnore
	private byte[] image;
	
	@Transient
	@JsonIgnore
	MultipartFile mpImage;

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
	
	
	
		public Photo(String title, String description, Boolean visibility, User user) {
		
		setTitle(title);
		setDescription(description);
		setVisibility(visibility);
		setUser(user);
		
	}
	
	
	public Photo (String title, String description, Boolean visibility, User user, Category... categories) {
		
		this(title, description, visibility, user);
		
		setCategories(categories);
		
	}
	
	public Photo (String title, String description, Boolean visibility, User user, Comment... comments) {
		
		this(title, description, visibility, user);
		
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
	
	public Boolean getVisibility() {
		return visibility;
	}
	
	public boolean hasImage() {
		
		return getImage() != null;
	}
	
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public MultipartFile getMpImage() {
		return mpImage;
	}

	public void setMpImage(MultipartFile mpImage) {
		
		try {
			
			setImage(mpImage.getBytes());
			
			this.mpImage = mpImage;
			
		} catch (IOException e) { }
		
	}
	
	public String getREImage() {
		
		return Base64.getEncoder().encodeToString(getImage());
	}
	
	@AssertTrue(message = "L'immagine è obbligatoria")
	public boolean isImagePresent() {
	    return (mpImage != null && !mpImage.isEmpty()) || (image != null && image.length > 0);
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
		
		return "[" + getId() + "] " + getTitle() + ", /n" + getDescription();
		
	}
	
	
	
}
