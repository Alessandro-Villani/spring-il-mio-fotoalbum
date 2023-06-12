package org.java.fotoalbum.controller.api;

import org.java.fotoalbum.pojo.Comment;
import org.java.fotoalbum.pojo.Photo;
import org.java.fotoalbum.services.CommentService;
import org.java.fotoalbum.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1")
public class CommentApiController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private PhotoService photoService;
	
	@PostMapping("/comments/store")
	public ResponseEntity<Comment> storeComment(@RequestBody CommentResponseDto commentResponseDto) {
		
		System.out.println(commentResponseDto);
		
		Photo photo = photoService.findById(commentResponseDto.getPhotoId()).get();
		
		Comment comment = new Comment(commentResponseDto.getContent(), photo);
		
		commentService.save(comment);
		
		return new ResponseEntity<>(comment, HttpStatus.OK);

	}

}
