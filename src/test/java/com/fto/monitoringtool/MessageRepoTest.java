package com.fto.monitoringtool;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.fto.monitoringtool.message.Message;
import com.fto.monitoringtool.message.MessageRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class MessageRepoTest {
	
	@Autowired
	private MessageRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateMessage() {
		Message msg = new Message();
		msg.setText("this is created for testing purpose");
		msg.setRequestTime(LocalDateTime.now());
		Message saveMessage = repo.save(msg);
		
		
		Message expectedMessage = entityManager.find(Message.class, saveMessage.getId());
		assertThat(expectedMessage.getText()).isEqualTo(saveMessage.getText());
	}
	
	@Test
	public void testCustomQueryMessage() {
		Message msg = new Message();
		msg.setText("this is created for testing purpose");
		msg.setRequestTime(LocalDateTime.now());
		Message save = repo.save(msg);
		
		Message msg_1 = new Message();
		msg_1.setText("this is created for testing purpose 1");
		msg_1.setRequestTime(LocalDateTime.now());
		Message save2 = repo.save(msg_1);
		
		Message msg_2 = new Message();
		msg_2.setText("this is created for testing purpose 2");
		msg_2.setRequestTime(LocalDateTime.now());
		Message save3 = repo.save(msg_2);
		List<Message> actualMsg = List.of(save2, save3);
		
		List<Message> expectMsg = repo.filterById(save2.getId(),save3.getId());
		
		assertThat(expectMsg.size() == 2);
		assertThat(actualMsg).isEqualTo(expectMsg);
		
	}

}
