package com.example.provider.controller;

import com.example.provider.service.HealthStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Value("${server.port}")
    String port;

    @Autowired
    HealthStatusService hs;

    @GetMapping("/hi")
    public String getHi(){
        return "hi!,my port is : "+port;
    }

    @GetMapping("/health")
    public String health(@RequestParam("status") Boolean status) {

        hs.setStatus(status);
        return hs.getStatus();
    }

}
