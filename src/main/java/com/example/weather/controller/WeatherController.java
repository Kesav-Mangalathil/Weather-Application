package com.example.weather.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class WeatherController {

    private final String API_KEY = "0dff81fb9b970726508483fce1bbb472\r\n" + //
                ""; 

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/weather")
    public String getWeather(@RequestParam String city, Model model) {
        try {
            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city +
                         "&units=metric&appid=" + API_KEY;

            RestTemplate restTemplate = new RestTemplate();
            Map response = restTemplate.getForObject(url, Map.class);

            Map main = (Map) response.get("main");
            Map weather = ((java.util.List<Map>) response.get("weather")).get(0);

            model.addAttribute("city", city);
            model.addAttribute("temperature", main.get("temp"));
            model.addAttribute("description", weather.get("description"));
            model.addAttribute("icon", weather.get("icon"));
        } catch (Exception e) {
            model.addAttribute("error", "City not found! Please try again.");
        }
        return "index";
    }
}

