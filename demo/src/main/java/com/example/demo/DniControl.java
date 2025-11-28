package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class DniControl {
    private final String API_TOKEN =
        "8312b98d63a96f20e57a9c855a9fa68fbd065da1631349a0569a9e3f147e0bcc";
    private final String API_URL ="https://apiperu.dev/api/dni";

    @GetMapping("/inicio")
    public String inicio() {
        return "pagina";
    }

    @PostMapping("/buscar")
    public String buscarDni(@RequestParam("dni") String dni, Model model) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + API_TOKEN);
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> body = new HashMap<>();
            body.put("dni", dni);

            HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);
            ResponseEntity<Map> response =
                restTemplate.exchange(API_URL, HttpMethod.POST, entity, 
                Map.class);

            Map data = (Map) response.getBody().get("data");

            // Extraer valores
            model.addAttribute("nombres", data.get("nombres"));
            model.addAttribute("apePat", data.get("apellido_paterno"));
            model.addAttribute("apeMat", data.get("apellido_materno"));
            model.addAttribute("dni", dni);
        } catch (Exception e) {
            model.addAttribute("error", "No se pudo encontrar el DNI");
        }
        return "pagina";
    }
}
