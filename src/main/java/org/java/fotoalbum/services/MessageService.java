package org.java.fotoalbum.services;

import java.util.List;
import java.util.Optional;

import org.java.fotoalbum.pojo.Message;
import org.java.fotoalbum.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

	@Autowired
	private MessageRepo messageRepo;
	
	public List<Message> findAll(){
		
		return messageRepo.findAll();
		
	}
	
	public Optional<Message> findById(int id){
		
		return messageRepo.findById(id);
		
	}
	
	public Message save(Message message) {
		
		return messageRepo.save(message);
		
	}
	
	public void delete(Message message) {
		
		messageRepo.delete(message);
		
	}
	
}
