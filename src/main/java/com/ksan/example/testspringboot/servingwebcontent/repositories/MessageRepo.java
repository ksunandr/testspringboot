package com.ksan.example.testspringboot.servingwebcontent.repositories;

import com.ksan.example.testspringboot.servingwebcontent.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Long> {
}
