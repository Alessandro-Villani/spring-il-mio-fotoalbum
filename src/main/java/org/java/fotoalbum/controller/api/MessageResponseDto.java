package org.java.fotoalbum.controller.api;

public class MessageResponseDto {
	
	private String email;
	private String message;
	private Integer photoId;
	
	public MessageResponseDto(String email, String message, int id) {
		
		setEmail(email);
		setMessage(message);
		setPhotoId(id);
		
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

	public Integer getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}
	
	
	
	
	
}
