package org.java.fotoalbum.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "L'email è obbligatoria")
	private String email;
	
	@NotBlank(message = "Il messaggio è obbligatorio")
	private String message;
	
	@ManyToOne
	@JsonBackReference
	private Photo photo;
	
	
	public Message() {}
	public Message(String email, String message, Photo photo) {
		
		setEmail(email);
		setMessage(message);
		setPhoto(photo);
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Photo getPhoto() {
		return photo;
	}
	
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	
	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getEmail() + ",/n" + getMessage();
	}
	

}
