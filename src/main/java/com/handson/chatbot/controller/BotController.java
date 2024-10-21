package com.handson.chatbot.controller;

import com.handson.chatbot.service.AmazonService;
import com.handson.chatbot.service.ImbdService;
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
    ImbdService imbdService;
    @Autowired
    WeatherService weatherService;  // Inject the WeatherService

    // Amazon products endpoint
    @RequestMapping(value = "/amazon", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@RequestParam String keyword) throws IOException {
        return new ResponseEntity<>(amazonService.searchProducts(keyword), HttpStatus.OK);
    }

    // IMDb movies endpoint
    @RequestMapping(value = "/imbd", method = RequestMethod.GET)
    public ResponseEntity<?> getMovies(@RequestParam String keyword) throws IOException {
        return new ResponseEntity<>(imbdService.searchMovies(keyword), HttpStatus.OK);
    }

    // Bot endpoint to handle weather queries using a query parameter for the city
    @RequestMapping(value = "/weather", method = {RequestMethod.GET})
    public ResponseEntity<String> getWeather(@RequestParam String city) throws IOException {
        String res = weatherService.getWeatherForCity(city);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
