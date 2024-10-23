package com.handson.chatbot.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    // Method to get weather details for a given city name
//    public String getWeatherForCity(String city) throws IOException {
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric")
//                .method("GET", null)
//                .build();
//
//        Response response = client.newCall(request).execute();
//        String res = response.body().string();
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        WeatherData weatherData = objectMapper.readValue(res, WeatherData.class);
//
//        try {
//            // Formatting the weather details
//            StringBuilder weatherDetails = new StringBuilder();
//            weatherDetails.append("Weather: ").append(weatherData.getWeather().get(0).getDescription()).append("\n");
//            weatherDetails.append("Temperature: ").append(weatherData.getMain().getTemp()).append(" °C\n");
//            weatherDetails.append("Feels Like: ").append(weatherData.getMain().getFeels_like()).append(" °C\n");
//            weatherDetails.append("Humidity: ").append(weatherData.getMain().getHumidity()).append("%\n");
//            weatherDetails.append("Wind Speed: ").append(weatherData.getWind().getSpeed()).append(" m/s");
//
//            return weatherDetails.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Weather details not found";
//        }
//    }

    // Method to get weather details for a given city name
    public String getWeatherForCity(String city) throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Add ",IL" country code for cities in Israel
        if (!city.toLowerCase().contains("il")) {
            city = city + ",IL";
        }

        Request request = new Request.Builder()
                .url("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric")
                .method("GET", null)
                .build();

        Response response = client.newCall(request).execute();
        String res = response.body().string();

        ObjectMapper objectMapper = new ObjectMapper();
        WeatherData weatherData = objectMapper.readValue(res, WeatherData.class);

        try {
            // Formatting the weather details
            StringBuilder weatherDetails = new StringBuilder();
            weatherDetails.append("Weather: ").append(weatherData.getWeather().get(0).getDescription()).append("\n");
            weatherDetails.append("Temperature: ").append(weatherData.getMain().getTemp()).append(" °C\n");
            weatherDetails.append("Feels Like: ").append(weatherData.getMain().getFeels_like()).append(" °C\n");
            weatherDetails.append("Humidity: ").append(weatherData.getMain().getHumidity()).append("%\n");
            weatherDetails.append("Wind Speed: ").append(weatherData.getWind().getSpeed()).append(" m/s");

            return weatherDetails.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Weather details not found";
        }
    }




    // Nested class to represent weather data
    @JsonIgnoreProperties(ignoreUnknown = true)  // Ignore unrecognized fields
    public static class WeatherData {
        private List<Weather> weather;
        private Main main;
        private Wind wind;

        public List<Weather> getWeather() {
            return weather;
        }

        public Main getMain() {
            return main;
        }

        public void setMain(Main main) {
            this.main = main;
        }

        public Wind getWind() {
            return wind;
        }

        public void setWind(Wind wind) {
            this.wind = wind;
        }
    }

    // Nested class to represent individual weather information
    @JsonIgnoreProperties(ignoreUnknown = true)  // Ignore unrecognized fields
    public static class Weather {
        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    // Nested class to represent the main weather information (temperature, humidity)
    @JsonIgnoreProperties(ignoreUnknown = true)  // Ignore unrecognized fields
    public static class Main {
        private double temp;
        private double feels_like;
        private int humidity;

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getFeels_like() {
            return feels_like;
        }

        public void setFeels_like(double feels_like) {
            this.feels_like = feels_like;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }
    }

    // Nested class to represent wind information
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Wind {
        private double speed;

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }
    }
}
