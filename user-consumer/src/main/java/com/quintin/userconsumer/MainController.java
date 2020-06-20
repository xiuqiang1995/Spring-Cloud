package com.quintin.userconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    ComsumerApi api;

    @Autowired
    RestService restService;

    @Value("${server.port}")
    String port;

    @GetMapping("/alive2")
    public String alive2(){
        return "Consumer ： " + port + " 》》》 "+restService.alive();
    }


    @GetMapping("/alive")
    public String alive(){
        System.out.println("api = alive");
        return api.alive();
    }


    @RequestMapping("/getById")
    public String getById(Integer id){
        System.out.println("id = " + id);
        return api.getById(id);
    }

    @GetMapping("/map")
    public String map(Integer id) {
        System.out.println(id);
        return api.getMap(id).toString();
    }

    @GetMapping("/map2")
    public String map2(Integer id,String name) {
        System.out.println(id);
        return api.getMap2(id,name).toString();
    }

    @GetMapping("/map3")
    public String map3(@RequestParam Map<String, Object> map) {
        System.out.println(map);
        return api.getMap3(map).toString();
    }

    @GetMapping("/map4")
    public String map4(@RequestParam Map<String, Object> map) {
        System.out.println(map);
        return api.postMap(map).toString();
    }
}
