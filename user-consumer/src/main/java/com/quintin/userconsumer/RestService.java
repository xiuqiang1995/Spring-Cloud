package com.quintin.userconsumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    @Autowired
    RestTemplate template;

    @HystrixCommand(defaultFallback = "back")
    public String alive() {

        String url = "http://user-provider/alive";

        String forObject = template.getForObject(url, String.class);

        return forObject;
    }

    public String back(){
        return "back";
    }
}
