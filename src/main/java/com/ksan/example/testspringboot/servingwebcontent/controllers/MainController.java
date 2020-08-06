package com.ksan.example.testspringboot.servingwebcontent.controllers;

import com.ksan.example.testspringboot.servingwebcontent.domain.Message;
import com.ksan.example.testspringboot.servingwebcontent.domain.User;
import com.ksan.example.testspringboot.servingwebcontent.repositories.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.soap.SOAPBinding;
import java.util.Map;

@Controller
public class MainController {

    // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping //it means "/"
    public String greeting(
            @RequestParam(name = "name", required = false, defaultValue = "World")
                    String name,
            Map<String, Object> model) {
        model.put("guest_name", name);
        return "greeting";
    }

    @GetMapping("/startPage")
    public String startPage(Map<String, Object> model) {
        model.put("start_info", "this is the start page");

        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "startPage";
    }



    @PostMapping("add")   // @RequestParam means it is a parameter from the GET or POST request
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
                      @RequestParam String tag,
                      Map<String, Object> model) {
        Message message = new Message(text, tag, user);
        messageRepo.save(message);


        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages", messages);
        model.put("start_info", "start_info");

        return "startPage";
    }

    @PostMapping("filter")
    public String filter(@RequestParam(name = "tag", required = false, defaultValue = "") String tag,
                         Map<String, Object> model) {
        Iterable<Message> messages;

        if (tag == null || tag.isEmpty()) {
            messages = messageRepo.findAll();
        } else {
            messages = messageRepo.findByTagStartingWith(tag);
        }
        model.put("messages", messages);
        model.put("start_info", "start_info");

        return "startPage";
    }

}