package com.ksan.example.testspringboot.servingwebcontent.repositories;

import com.ksan.example.testspringboot.servingwebcontent.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {

        List<Message> findByTag(String tag);
        List<Message> findByTagStartingWith(String tag);
        }
