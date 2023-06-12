package org.java.fotoalbum.controller.api;

public class CommentResponseDto {
	
	private String content;
	
	private int photoId;
	
	public CommentResponseDto() {};
	public CommentResponseDto(String content, int photoId) {
		
		setContent(content);
		setPhotoId(photoId);
		
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	
	@Override
	public String toString() {
		
		return getContent() + ", " + getPhotoId();
	}
	
}
