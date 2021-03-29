package com.fto.monitoringtool.message;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public interface MessageRepository extends JpaRepository<Message, Long> {
	@Query("SELECT m FROM Message m where m.id >= ?1 and m.id <= ?2")
	List<Message> filterById(Long first, Long last);
	
}
