package com.fto.monitoringtool.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
public class MessageController {
	@Autowired
	private MessageService repo;
	@GetMapping("")
	public ModelAndView openHomePage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	@GetMapping("/message_register")
	public ModelAndView openMonitoringPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("message_register");
		mv.addObject("messageKey", new Message());
		return mv;
	}
	@PostMapping("/success_send_message")
	public String startPorcess(Message message) {
		if(message == null ) return "result-failed";
		Message saveMessage = repo.saveMessage(message);
		if(message.getId() == null) return "result-failed";
		return "result-success";
	}
	@GetMapping("/messages")
	public ModelAndView getFilterMessages(
			@RequestParam(value = "f", required = false) Long f,
			@RequestParam(value = "l", required = false) Long l) {
		if(l== null || l == 0) l = Long.MAX_VALUE;
		if(f == null) f = 0l;
		ModelAndView mv = new ModelAndView();
		mv.setViewName("messages");
		List<Message> messageList = repo.getFilterMessages(f,l);
		mv.addObject("messageList", messageList );
		return mv;
	}


}
