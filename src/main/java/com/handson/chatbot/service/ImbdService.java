package com.handson.chatbot.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ImbdService {
    public String searchMovies(String keyword) throws IOException {
        System.out.println("The keyword is :" + keyword);
        return "Searched for: " + keyword;
    }
}
