package org.java.fotoalbum.controller.api;

import org.java.fotoalbum.pojo.Message;
import org.java.fotoalbum.pojo.Photo;
import org.java.fotoalbum.services.MessageService;
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
@RequestMapping("/api/v1")
public class MessageApiController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private PhotoService photoService;
	
	@PostMapping("/messages/store")
	public ResponseEntity<Message> storeMessage(@RequestBody MessageResponseDto messageResponseDto){
		
		Photo photo = photoService.findById(messageResponseDto.getPhotoId()).get();
		
		
		Message message = new Message(messageResponseDto.getEmail(), messageResponseDto.getMessage(), photo);
		
		messageService.save(message);
		
		return new ResponseEntity<>(message, HttpStatus.OK);
		
	}

}
