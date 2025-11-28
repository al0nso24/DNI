package com.example.demo;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReniecService {
    private final String API_TOKEN =
        "914dbef8372327d4839a63dd83edebab38233f5ece768ea7c30fe5ecb8d3bae6";
    private final String API_URL ="https://apiperu.dev/api/dni";

    public Object consultarDni(String dni) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + API_TOKEN);
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(headers);
            String url = API_URL + "/" + dni;

            ResponseEntity<Object> response =
                restTemplate.exchange(url, HttpMethod.GET, entity,
                 Object.class);

            return response.getBody();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
