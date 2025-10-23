package com.example.weather.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Controller
public class WeatherController {

    private final String API_KEY = "4cd3b929f6ebd22679951a3831a3d5d1";

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/weather")
    public String getWeather(@RequestParam String city, Model model) {

        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city +
                     "&units=metric&appid=" + API_KEY;

        RestTemplate restTemplate = new RestTemplate();

        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            Map<String, Object> main = (Map<String, Object>) response.get("main");
            Map<String, Object> weather = ((List<Map<String, Object>>) response.get("weather")).get(0);

            model.addAttribute("city", city);
            model.addAttribute("temperature", main.get("temp"));
            model.addAttribute("description", weather.get("description"));
            model.addAttribute("icon", weather.get("icon"));

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 404) {
                model.addAttribute("error", "City not found! Please try again.");
            } else if (e.getStatusCode().value() == 401) {
                model.addAttribute("error", "API Key is invalid. Please check your key.");
            } else {
                model.addAttribute("error", "An error occurred: " + e.getStatusCode());
            }
        } catch (Exception e) {
            model.addAttribute("error", "An unknown error occurred. Please try again later.");
        }

        return "index";
    }
}
