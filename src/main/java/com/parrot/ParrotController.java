package com.parrot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ParrotController {

    private final long startTime = System.currentTimeMillis();

    @Value("${PARROT_COLOR:unknown}")
    private String color;

    @GetMapping("/")
    public Map<String, Object> getInfo() {
        Map<String, Object> response = new HashMap<>();
        
        response.put("hostname", getHostname());
        response.put("timestamp", Instant.now().toString());
        response.put("uptime", (System.currentTimeMillis() - startTime) / 1000);
        response.put("color", color);
        response.put("env", System.getenv());
        
        return response;
    }

    private String getHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "unknown";
        }
    }
}
