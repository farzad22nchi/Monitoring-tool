package com.fto.monitoringtool.message;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
	@Autowired
	private MessageRepository repo;
	
	public Message saveMessage(Message message) {
		message.setRequestTime(LocalDateTime.now());
		return repo.save(message);
	}
	
	public Message getMessageById(Long id) {
		return this.repo.findById(id).orElseThrow(
				()-> new MessageExceptionHandler("the message with id "+ id + "does not exist"));
	}
	
	public List<Message> getAllMessages(){
		return this.repo.findAll();
	}
	public List<Message> getFilterMessages(Long first, Long last){
		return this.repo.filterById(first, last);
	}
}
