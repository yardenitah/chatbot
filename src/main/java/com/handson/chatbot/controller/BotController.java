package com.handson.chatbot.controller;

import com.handson.chatbot.service.AmazonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bot")
public class BotController {

    @Autowired
    AmazonService amazonService;

    @RequestMapping(value = "/amazon", method = RequestMethod.GET)
    public ResponseEntity<?> getOneStudent(@RequestParam String keyword)
    {
        return new ResponseEntity<>(amazonService.searchProducts(keyword), HttpStatus.OK);
    }
}
