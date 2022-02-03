package com.handson.chatbot.controller;

import com.handson.chatbot.service.AmazonService;
import com.handson.chatbot.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/bot")
public class BotController {

    @Autowired
    AmazonService amazonService;

    @Autowired
    WeatherService weatherService;

    @RequestMapping(value = "/amazon", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@RequestParam String keyword) throws IOException {
        return new ResponseEntity<>(amazonService.searchProducts(keyword), HttpStatus.OK);
    }

    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    public ResponseEntity<?> getWeather(@RequestParam String location) throws IOException {
        return new ResponseEntity<>(weatherService.searchWeather(location), HttpStatus.OK);
    }

}
